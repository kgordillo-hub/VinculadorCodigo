package co.mlforex.forecast.repository;

import co.mlforex.forecast.model.TransaccionInfo;

import java.util.List;
import java.util.Optional;

public interface VincularCodigoRepo {

    void guardarInfo(TransaccionInfo transaccionInfo);

    Optional<List<TransaccionInfo>> consultarVinculaciones(String idUsuario);

    void actualizarInfo(TransaccionInfo transaccionInfo);
}
