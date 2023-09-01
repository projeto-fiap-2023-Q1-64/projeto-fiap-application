package br.fiap.projeto.contexto.comanda.external.api;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusRecebidoComandaControllerAdapter;
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
public class BuscaRecebidoComandaApiExternal {

    private final IBuscaPorStatusRecebidoComandaControllerAdapter buscarPorStatusRecebidoComandaControlleAdapter;

    @Autowired
    public BuscaRecebidoComandaApiExternal(
            IBuscaPorStatusRecebidoComandaControllerAdapter buscarPorStatusRecebidoComandaControlleAdapter) {
        this.buscarPorStatusRecebidoComandaControlleAdapter = buscarPorStatusRecebidoComandaControlleAdapter;
    }

    @SneakyThrows
    @GetMapping("/busca-recebido")
    @ResponseBody
    @ApiOperation(value = "Busca todas as comandas recebidas", notes = "Este endpoint retorna uma lista de comandas recebidas (pendentes)")
    ResponseEntity<List<ComandaDTO>> getComandasRecebidas() {
        final List<ComandaDTO> lista = this.buscarPorStatusRecebidoComandaControlleAdapter
                .buscaPorStatusRecebido();
        return ResponseEntity.ok().body(lista);
    }

}
