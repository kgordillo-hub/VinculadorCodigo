package co.mlforex.forecast.vinculadorCodigo.controller;

import co.mlforex.forecast.vinculadorCodigo.exceptions.BusinessException;
import co.mlforex.forecast.vinculadorCodigo.model.TransaccionInfo;
import co.mlforex.forecast.vinculadorCodigo.service.VinculadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VinculadorController {

    Logger logger = LoggerFactory.getLogger(VinculadorController.class);

    @Autowired
    VinculadorService vinculadorService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/vincularCodigo")
    public ResponseEntity<String> vincularAlgoritmo(@RequestBody TransaccionInfo transaccionInfo) {
        try {
            if (vinculadorService.vincularAlgoritmo(transaccionInfo)) {
                return new ResponseEntity<>("Vinculado", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("El algoritmo ya fue vinculado", HttpStatus.ALREADY_REPORTED);
            }
        } catch (BusinessException e) {
            return new ResponseEntity<>("El algoritmo a vincular aún está en procesamiento...", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/consultarVinculaciones/{idUsuario}")
    public ResponseEntity<List<TransaccionInfo>> consultarVinculaciones(@PathVariable String idUsuario) {
        try {
            final List<TransaccionInfo> listaTransacciones = vinculadorService.consultarVinculaciones(idUsuario);
            return new ResponseEntity<>(listaTransacciones, HttpStatus.OK);
        } catch (final Exception e) {
            logger.error("Error en VinculadorController:consultarVinculaciones" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/actualizarVinculacion/{evento}")
    public ResponseEntity<String> actulizarVinculacion(@RequestBody TransaccionInfo transaccionInfo, @PathVariable String evento) {
        try {
            vinculadorService.actualizarEstado(transaccionInfo, evento);
        } catch (final Exception e) {
            logger.error("Error en VinculadorController:actulizarVinculacion" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
}
