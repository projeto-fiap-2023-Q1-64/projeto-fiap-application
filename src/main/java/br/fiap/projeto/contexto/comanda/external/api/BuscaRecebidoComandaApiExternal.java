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
public class BuscaRecebidoComandaApiExternal {

    private final IBuscarPorStatusComandaRepositoryPortUseCase buscaRecebidoComandaRepositoryPortUseCase;

    @Autowired
    public BuscaRecebidoComandaApiExternal(
            IBuscarPorStatusComandaRepositoryPortUseCase buscaRecebidoComandaRepositoryPortUseCase) {
        this.buscaRecebidoComandaRepositoryPortUseCase = buscaRecebidoComandaRepositoryPortUseCase;
    }

    @GetMapping("/busca-pendentes")
    @ResponseBody
    ResponseEntity<List<Comanda>> getComandasPendentes() throws ExceptionMessage, Exception {
        final List<Comanda> lista = this.buscaRecebidoComandaRepositoryPortUseCase
                .buscaComandaPorStatus(StatusComanda.RECEBIDO);
        return ResponseEntity.ok().body(lista);
    }

}
