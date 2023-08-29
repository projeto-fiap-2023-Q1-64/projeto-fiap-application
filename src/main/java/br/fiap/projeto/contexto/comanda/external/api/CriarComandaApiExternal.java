package br.fiap.projeto.contexto.comanda.external.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.ICriarComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.CriarComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

@RestController
@RequestMapping("/comandas")
public class CriarComandaApiExternal {

    private final ICriarComandaControllerAdapter criarComandaControllerAdapter;

    @Autowired
    public CriarComandaApiExternal(ICriarComandaControllerAdapter criarComandaControllerAdapter) {
        this.criarComandaControllerAdapter = criarComandaControllerAdapter;
    }

    @PostMapping
    ResponseEntity<ComandaDTO> criaComanda(@RequestBody CriarComandaDTO criarComandaDTO) throws ExceptionMessage {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.criarComandaControllerAdapter.criaComanda(criarComandaDTO));
    }

}
