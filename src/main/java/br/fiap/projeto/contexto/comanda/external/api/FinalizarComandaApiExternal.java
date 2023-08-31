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
public class FinalizarComandaApiExternal {

    private final IAtualizaComandaControllerAdapter finalizaComandaPortControllerAdapter;

    @Autowired
    public FinalizarComandaApiExternal(IAtualizaComandaControllerAdapter finalizaComandaControllerAdapter) {
        this.finalizaComandaPortControllerAdapter = finalizaComandaControllerAdapter;
    }

    @PatchMapping("/{codigo-comanda}/finalizar")
    @ResponseBody
    ResponseEntity<ComandaDTO> finalizar(@PathVariable("codigo-comanda") UUID codigoComando)
            throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.finalizaComandaPortControllerAdapter.atualizaStatusComanda(codigoComando));
    }

}
