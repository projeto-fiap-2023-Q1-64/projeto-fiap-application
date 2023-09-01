package br.fiap.projeto.contexto.comanda.external.api;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comandas")
@Api(tags = {"Comanda"}, description = "Endpoint do domínio de comanda")
public class PrepararComandaApiExternal {

    private final IAtualizaComandaControllerAdapter preparaComandaControllerAdapter;

    @Autowired
    public PrepararComandaApiExternal(IAtualizaComandaControllerAdapter preparaComandaControllerAdapter) {
        this.preparaComandaControllerAdapter = preparaComandaControllerAdapter;
    }

    @PatchMapping("/{codigo-comanda}/preparar")
    @ResponseBody
    @ApiOperation(value = "Inicia o preparo de uma comanda", notes = "Este endpoint informa o início do preparo de uma comanda")
    ResponseEntity<ComandaDTO> preparar(@PathVariable("codigo-comanda") UUID codigoComando)
            throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.preparaComandaControllerAdapter.atualizaStatusComanda(codigoComando));
    }

}
