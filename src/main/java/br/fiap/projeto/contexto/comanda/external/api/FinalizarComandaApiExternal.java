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
public class FinalizarComandaApiExternal {

    private final IAtualizaComandaControllerAdapter finalizaComandaPortControllerAdapter;

    @Autowired
    public FinalizarComandaApiExternal(IAtualizaComandaControllerAdapter finalizaComandaControllerAdapter) {
        this.finalizaComandaPortControllerAdapter = finalizaComandaControllerAdapter;
    }

    @PatchMapping("/{codigo-comanda}/finalizar")
    @ResponseBody
    @ApiOperation(value = "Finaliza uma comanda", notes = "Este endpoint informa a finalização da comanda")
    ResponseEntity<ComandaDTO> finalizar(@PathVariable("codigo-comanda") UUID codigoComando)
            throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.finalizaComandaPortControllerAdapter.atualizaStatusComanda(codigoComando));
    }

}
