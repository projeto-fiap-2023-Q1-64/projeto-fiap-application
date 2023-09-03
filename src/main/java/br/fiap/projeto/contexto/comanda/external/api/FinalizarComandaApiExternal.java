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

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comandas")
@Api(tags = { "Comanda" }, description = "Endpoint do domínio de comanda")
public class FinalizarComandaApiExternal {

    private final IAtualizaComandaControllerAdapter finalizaComandaPortControllerAdapter;

    @Autowired
    public FinalizarComandaApiExternal(IAtualizaComandaControllerAdapter finalizaComandaControllerAdapter) {
        this.finalizaComandaPortControllerAdapter = finalizaComandaControllerAdapter;
    }

    @PatchMapping("/{codigo-pedido}/finalizar")
    @ResponseBody
    @ApiOperation(value = "Finaliza uma comanda", notes = "Este endpoint informa a finalização da comanda")
    ResponseEntity<ComandaDTO> finalizar(@PathVariable("codigo-pedido") UUID codigoPedido)
            throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.finalizaComandaPortControllerAdapter.atualizaStatusComanda(codigoPedido));
    }

}
