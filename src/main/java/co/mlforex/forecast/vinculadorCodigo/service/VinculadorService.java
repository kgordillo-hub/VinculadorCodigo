package co.mlforex.forecast.vinculadorCodigo.service;

import co.mlforex.forecast.vinculadorCodigo.exceptions.BusinessException;
import co.mlforex.forecast.vinculadorCodigo.model.TransaccionInfo;

import java.util.List;

public interface VinculadorService {

    Boolean vincularAlgoritmo(TransaccionInfo transaccionInfo) throws BusinessException;

    List<TransaccionInfo> consultarVinculaciones(String idUsuario);

    void actualizarEstado(TransaccionInfo transaccionInfo, String evento);

}
