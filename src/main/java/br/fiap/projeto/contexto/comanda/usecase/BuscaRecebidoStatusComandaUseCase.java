package br.fiap.projeto.contexto.comanda.usecase;

import java.util.List;
import java.util.stream.Collectors;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusRecebidoComandaRepositoryUseCase;

public class BuscaRecebidoStatusComandaUseCase implements IBuscaPorStatusComandaUseCase {

    private final IBuscarPorStatusRecebidoComandaRepositoryUseCase buscarPorStatusRecebidoComandaRepositoryUseCase;

    public BuscaRecebidoStatusComandaUseCase(
            IBuscarPorStatusRecebidoComandaRepositoryUseCase buscarPorStatusRecebidoComandaRepositoryUseCase) {
        this.buscarPorStatusRecebidoComandaRepositoryUseCase = buscarPorStatusRecebidoComandaRepositoryUseCase;
    }

    @Override
    public List<Comanda> buscaComandaPorStatus(StatusComanda status) throws ExceptionMessage, Exception {
        status = StatusComanda.RECEBIDO;
        return buscarPorStatusRecebidoComandaRepositoryUseCase.buscaComandaPorStatus(status).stream()
                .collect(Collectors.toList());
    }

}
