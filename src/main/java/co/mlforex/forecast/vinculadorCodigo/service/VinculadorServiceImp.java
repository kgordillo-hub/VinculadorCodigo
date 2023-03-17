package co.mlforex.forecast.vinculadorCodigo.service;

import co.mlforex.forecast.vinculadorCodigo.exceptions.BusinessException;
import co.mlforex.forecast.vinculadorCodigo.model.Mensaje;
import co.mlforex.forecast.vinculadorCodigo.model.TransaccionInfo;
import co.mlforex.forecast.vinculadorCodigo.notification.NotificadorSns;
import co.mlforex.forecast.vinculadorCodigo.repository.VincularCodigoRepo;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VinculadorServiceImp implements VinculadorService {

    Logger logger = LoggerFactory.getLogger(VinculadorServiceImp.class);

    @Autowired
    VincularCodigoRepo vinculadorRepo;
    @Value("${aws.sns.topic.vinculacionOut.arn}")
    String snsTopicVinculacionOut;

    @Value("${aws.sns.topic.analisisIn.arn}")
    String snsTopicAnalisisCod;

    @Override
    public Boolean vincularAlgoritmo(TransaccionInfo transaccionInfo) throws BusinessException {
        Optional<TransaccionInfo> ti = vinculadorRepo.findById(transaccionInfo.generateUID());
        if (ti.isEmpty()) {
            final Mensaje tiM = transaccionInfo.getMensaje();
            if (tiM != null && tiM.getLinkRepo() != null && !tiM.getLinkRepo().isEmpty() && tiM.getIdUsuario() != null && !tiM.getIdUsuario().isEmpty()) {
                transaccionInfo.setUID(transaccionInfo.generateUID());
                vinculadorRepo.save(transaccionInfo);
                transaccionInfo.setIdTransaccion(UUID.randomUUID().toString());
                final String message = new GsonBuilder().disableHtmlEscaping().create().toJson(transaccionInfo);
                new NotificadorSns().publishMessageSns(message, snsTopicAnalisisCod);
                return Boolean.TRUE;
            }
        } else if (ti.get().getMensaje().getCodigoVulnerable() == null || ti.get().getMensaje().getImagenGenerada() == null || ti.get().getMensaje().getOpenAPIFileCorrect() == null) {
            throw new BusinessException("El algoritmo a vincular aún está en procesamiento...");
        } else if (ti.get().getMensaje().getCodigoVulnerable() || !ti.get().getMensaje().getImagenGenerada() || !ti.get().getMensaje().getOpenAPIFileCorrect()) {
            transaccionInfo.setUID(transaccionInfo.generateUID());
            vinculadorRepo.save(transaccionInfo);

            transaccionInfo.setIdTransaccion(UUID.randomUUID().toString());
            final String message = new GsonBuilder().disableHtmlEscaping().create().toJson(transaccionInfo);
            new NotificadorSns().publishMessageSns(message, snsTopicAnalisisCod);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<TransaccionInfo> consultarVinculaciones(String idUsuario) {
        final List<TransaccionInfo> listaTransacciones = new ArrayList<>();
        final Iterable<TransaccionInfo> transactions = vinculadorRepo.findAll();
        if (transactions != null) {
            final Iterator<TransaccionInfo> it = transactions.iterator();
            while (it.hasNext()) {
                final TransaccionInfo ti = it.next();
                if (ti.getMensaje().getIdUsuario() != null && ti.getMensaje().getIdUsuario().equalsIgnoreCase(idUsuario)) {
                    listaTransacciones.add(ti);
                }
            }
        }
        return listaTransacciones;
    }

    @Override
    public void actualizarEstado(TransaccionInfo transaccionInfo, String evento) {
        Optional<TransaccionInfo> transactionDb = vinculadorRepo.findById(transaccionInfo.generateUID());
        if (!transactionDb.isEmpty()) {
            if (evento.equalsIgnoreCase("genImagen")) {
                transactionDb.get().getMensaje().setImagenGenerada(transaccionInfo.getMensaje().getImagenGenerada());
            } else if (evento.equalsIgnoreCase("vulnerabilidad")) {
                transactionDb.get().getMensaje().setCodigoVulnerable(transaccionInfo.getMensaje().getCodigoVulnerable());
            } else if (evento.equalsIgnoreCase("APISpec")) {
                transactionDb.get().getMensaje().setOpenAPIFileCorrect(transaccionInfo.getMensaje().getOpenAPIFileCorrect());
            }
            vinculadorRepo.save(transactionDb.get());

            final Boolean codVulnerable = transactionDb.get().getMensaje().getCodigoVulnerable() == null ?
                    Boolean.TRUE : transactionDb.get().getMensaje().getCodigoVulnerable();
            final Boolean imgGenerada = transactionDb.get().getMensaje().getImagenGenerada() == null ?
                    Boolean.FALSE : transactionDb.get().getMensaje().getImagenGenerada();
            final Boolean apiSpec = transactionDb.get().getMensaje().getOpenAPIFileCorrect() == null ?
                    Boolean.FALSE : transactionDb.get().getMensaje().getOpenAPIFileCorrect();

            if (!codVulnerable && imgGenerada && apiSpec) {
                final String message = new GsonBuilder().disableHtmlEscaping().create().toJson(transactionDb.get());
                new NotificadorSns().publishMessageSns(message, snsTopicVinculacionOut);
            }
        }
    }

}
