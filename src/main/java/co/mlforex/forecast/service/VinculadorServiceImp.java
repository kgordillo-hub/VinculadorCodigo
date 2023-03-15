package co.mlforex.forecast.service;

import co.mlforex.forecast.model.TransaccionInfo;
import co.mlforex.forecast.repository.VincularCodigoRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VinculadorServiceImp implements VinculadorService{

    @Autowired
    VincularCodigoRepo vinculadorRepo;

    @Override
    public void vincularAlgoritmo(TransaccionInfo transaccionInfo) {
        vinculadorRepo.guardarInfo(transaccionInfo);
    }

    @Override
    public List<TransaccionInfo> consultarVinculaciones(String idUsuario) {
        return vinculadorRepo.consultarVinculaciones(idUsuario).get();
    }

    @Override
    public void actualizarInfoAlgoritmo(TransaccionInfo transaccionInfo) {
        vinculadorRepo.actualizarInfo(transaccionInfo);
    }
}
