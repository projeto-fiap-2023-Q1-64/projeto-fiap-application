package br.fiap.projeto.contexto.comanda.external.api;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusFinalizadoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comandas")
@Api(tags = {"Comanda"}, description = "Endpoints do domínio de comanda")
public class BuscaFinalizadoStatusComandaApiExternal {

    private final IBuscaPorStatusFinalizadoComandaControllerAdapter buscaFinalizadoStatusComandaControllerAdapter;

    @Autowired
    public BuscaFinalizadoStatusComandaApiExternal(IBuscaPorStatusFinalizadoComandaControllerAdapter buscaFinalizadoStatusComandaControllerAdapter) {
        this.buscaFinalizadoStatusComandaControllerAdapter = buscaFinalizadoStatusComandaControllerAdapter;
    }

    @SneakyThrows
    @GetMapping("/busca-finalizado")
    @ResponseBody
    @ApiOperation(value = "Busca todas as comandas finalizadas", notes = "Este endpoint retorna uma lista de comandas finalizadas")
    ResponseEntity<List<ComandaDTO>> getComandasFinalizadas() {
        List<ComandaDTO> lista = this.buscaFinalizadoStatusComandaControllerAdapter.buscaPorStatusFinalizado();
        return ResponseEntity.ok().body(lista);
    }

}
