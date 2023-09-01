package br.fiap.projeto.contexto.comanda.external.api;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusPreparacaoComandaControllerAdapter;
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
@Api(tags = {"Comanda"}, description = "Endpoint do domínio de comanda")
public class BuscaPreparacaoStatusComandaApiExternal {

    private final IBuscaPorStatusPreparacaoComandaControllerAdapter buscarPorStatusPreparacaoComandaControlleAdapter;

    @Autowired
    public BuscaPreparacaoStatusComandaApiExternal(
            IBuscaPorStatusPreparacaoComandaControllerAdapter buscarPorStatusPreparacaoComandaControlleAdapter) {
        this.buscarPorStatusPreparacaoComandaControlleAdapter = buscarPorStatusPreparacaoComandaControlleAdapter;
    }

    @SneakyThrows
    @GetMapping("/busca-preparacao")
    @ResponseBody
    @ApiOperation(value = "Busca todas as comandas preparação", notes = "Este endpoint retorna uma lista de comandas que estão em preparo")
    ResponseEntity<List<ComandaDTO>> getComandasPreparacao() {
        final List<ComandaDTO> lista = this.buscarPorStatusPreparacaoComandaControlleAdapter
                .buscaPorStatusPreparacao();
        return ResponseEntity.ok().body(lista);
    }

}
