package br.fiap.projeto.contexto.comanda.external.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaPortControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;

@RestController
@RequestMapping("/comandas")
public class PrepararComandaApiExternal {

    private final IAtualizaComandaPortControllerAdapter atualizarComandaPortControllerAdapter;

    @Autowired
    public PrepararComandaApiExternal(IAtualizaComandaPortControllerAdapter atualizarComandaPortControllerAdapter) {
        this.atualizarComandaPortControllerAdapter = atualizarComandaPortControllerAdapter;
    }

    @PatchMapping("/{codigo-comanda}/preparar")
    @ResponseBody
    ResponseEntity<ComandaDTO> preparar(@PathVariable("codigo-comanda") UUID codigoComando)
            throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.atualizarComandaPortControllerAdapter.atualizaStatusComanda(codigoComando));
    }

}
