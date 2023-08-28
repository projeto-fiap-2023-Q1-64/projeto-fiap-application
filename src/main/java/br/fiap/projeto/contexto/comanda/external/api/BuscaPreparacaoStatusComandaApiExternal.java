package br.fiap.projeto.contexto.comanda.external.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryPortUseCase;

@RestController
@RequestMapping("/comandas")
public class BuscaPreparacaoStatusComandaApiExternal {

    private final IBuscarPorStatusComandaRepositoryPortUseCase buscaPorStatusComandaPortControllerAdapter;

    @Autowired
    public BuscaPreparacaoStatusComandaApiExternal(
            IBuscarPorStatusComandaRepositoryPortUseCase buscaPorStatusComandaPortControllerAdapter) {
        this.buscaPorStatusComandaPortControllerAdapter = buscaPorStatusComandaPortControllerAdapter;
    }

    @GetMapping("/busca-pendentes")
    @ResponseBody
    ResponseEntity<List<Comanda>> getComandasPendentes() throws ExceptionMessage, Exception {
        final List<Comanda> lista = this.buscaPorStatusComandaPortControllerAdapter
                .buscaComandaPorStatus(StatusComanda.EM_PREPARACAO);
        return ResponseEntity.ok().body(lista);
    }

}
