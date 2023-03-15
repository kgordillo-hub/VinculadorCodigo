package co.mlforex.forecast.repository;

import co.mlforex.forecast.model.TransaccionInfo;

import java.util.List;
import java.util.Optional;

public class VincularCodigoRepoImp implements VincularCodigoRepo{
    @Override
    public void guardarInfo(TransaccionInfo transaccionInfo) {

    }

    @Override
    public Optional<List<TransaccionInfo>> consultarVinculaciones(String idUsuario) {
        return Optional.empty();
    }

    @Override
    public void actualizarInfo(TransaccionInfo transaccionInfo) {

    }
}
