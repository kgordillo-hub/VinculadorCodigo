package co.mlforex.forecast.service;

import co.mlforex.forecast.model.TransaccionInfo;

import java.util.List;

public interface VinculadorService {

    void vincularAlgoritmo(TransaccionInfo transaccionInfo);

    List<TransaccionInfo> consultarVinculaciones(String idUsuario);

    void actualizarInfoAlgoritmo(TransaccionInfo transaccionInfo);
}
