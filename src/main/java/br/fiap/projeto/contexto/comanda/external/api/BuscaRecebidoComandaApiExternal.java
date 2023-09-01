package br.fiap.projeto.contexto.comanda.external.api;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusRecebidoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comandas")
public class BuscaRecebidoComandaApiExternal {

    private final IBuscaPorStatusRecebidoComandaControllerAdapter buscarPorStatusRecebidoComandaControlleAdapter;

    @Autowired
    public BuscaRecebidoComandaApiExternal(
            IBuscaPorStatusRecebidoComandaControllerAdapter buscarPorStatusRecebidoComandaControlleAdapter) {
        this.buscarPorStatusRecebidoComandaControlleAdapter = buscarPorStatusRecebidoComandaControlleAdapter;
    }

    @GetMapping("/busca-recebido")
    @ResponseBody
    ResponseEntity<List<ComandaDTO>> getComandasPendentes() throws ExceptionMessage, Exception {
        final List<ComandaDTO> lista = this.buscarPorStatusRecebidoComandaControlleAdapter
                .buscaPorStatusRecebido();
        return ResponseEntity.ok().body(lista);
    }

}
