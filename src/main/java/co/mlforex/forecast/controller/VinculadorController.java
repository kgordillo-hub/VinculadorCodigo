package co.mlforex.forecast.controller;

import co.mlforex.forecast.model.TransaccionInfo;
import co.mlforex.forecast.service.VinculadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VinculadorController {

    @Autowired
    VinculadorService vinculadorService;

    @PostMapping("/vincularCodigo")
    public ResponseEntity<String> vincularAlgoritmo(TransaccionInfo transaccionInfo){
        vinculadorService.vincularAlgoritmo(transaccionInfo);
        return new ResponseEntity<>("Vinculated", HttpStatus.OK);
    }

    @PostMapping("/consultarVinculaciones")
    public ResponseEntity<List<TransaccionInfo>> consultarVinculaciones(String idUsuario){
        final List<TransaccionInfo> listaTransacciones = vinculadorService.consultarVinculaciones(idUsuario);
        return new ResponseEntity<>(listaTransacciones, HttpStatus.OK);
    }

    @PostMapping("/actualizarVinculacion")
    public ResponseEntity<String> actulizarVinculacion(TransaccionInfo transaccionInfo){
        vinculadorService.actualizarInfoAlgoritmo(transaccionInfo);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
}
