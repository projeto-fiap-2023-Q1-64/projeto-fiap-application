package br.fiap.projeto.contexto.comanda.external.api;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comandas")
public class PrepararComandaApiExternal {

    private final IAtualizaComandaControllerAdapter preparaComandaControllerAdapter;

    @Autowired
    public PrepararComandaApiExternal(IAtualizaComandaControllerAdapter preparaComandaControllerAdapter) {
        this.preparaComandaControllerAdapter = preparaComandaControllerAdapter;
    }

    @PatchMapping("/{codigo-comanda}/preparar")
    @ResponseBody
    ResponseEntity<ComandaDTO> preparar(@PathVariable("codigo-comanda") UUID codigoComando)
            throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.preparaComandaControllerAdapter.atualizaStatusComanda(codigoComando));
    }

}
