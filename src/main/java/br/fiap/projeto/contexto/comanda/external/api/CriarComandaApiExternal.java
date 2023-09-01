package br.fiap.projeto.contexto.comanda.external.api;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.ICriarComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.CriarComandaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comandas")
@Api(tags = {"Comanda"}, description = "Endpoint do domínio de comanda")
public class CriarComandaApiExternal {

    private final ICriarComandaControllerAdapter criarComandaControllerAdapter;

    @Autowired
    public CriarComandaApiExternal(ICriarComandaControllerAdapter criarComandaControllerAdapter) {
        this.criarComandaControllerAdapter = criarComandaControllerAdapter;
    }

    @SneakyThrows
    @PostMapping
    @ApiOperation(value = "Cria uma comanda", notes = "Este endpoint recebe os dados do pedido para criar uma comanda")
    ResponseEntity<ComandaDTO> criaComanda(@RequestBody CriarComandaDTO criarComandaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.criarComandaControllerAdapter.criaComanda(criarComandaDTO));
    }

}
