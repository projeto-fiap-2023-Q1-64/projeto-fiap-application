package br.fiap.projeto.contexto.comanda.external.api;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusPreparacaoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.entity.Comanda;
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
public class BuscaPreparacaoStatusComandaApiExternal {

    private final IBuscaPorStatusPreparacaoComandaControllerAdapter buscarPorStatusPreparacaoComandaControlleAdapter;

    @Autowired
    public BuscaPreparacaoStatusComandaApiExternal(
            IBuscaPorStatusPreparacaoComandaControllerAdapter buscarPorStatusPreparacaoComandaControlleAdapter) {
        this.buscarPorStatusPreparacaoComandaControlleAdapter = buscarPorStatusPreparacaoComandaControlleAdapter;
    }

    @GetMapping("/busca-preparacao")
    @ResponseBody
    ResponseEntity<List<ComandaDTO>> getComandasPendentes() throws ExceptionMessage, Exception {
        final List<ComandaDTO> lista = this.buscarPorStatusPreparacaoComandaControlleAdapter
                .buscaPorStatusPreparacao();
        return ResponseEntity.ok().body(lista);
    }

}
