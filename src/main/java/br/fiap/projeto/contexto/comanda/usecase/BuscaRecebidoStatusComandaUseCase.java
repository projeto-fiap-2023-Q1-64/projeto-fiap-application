package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaRecebidoStatusComandaUseCase implements IBuscaPorStatusRecebidoComandaUseCase {

    private final IBuscarPorStatusRecebidoComandaRepositoryUseCase buscarPorStatusRecebidoComandaRepositoryUseCase;

    public BuscaRecebidoStatusComandaUseCase(
            IBuscarPorStatusRecebidoComandaRepositoryUseCase buscarPorStatusRecebidoComandaRepositoryUseCase) {
        this.buscarPorStatusRecebidoComandaRepositoryUseCase = buscarPorStatusRecebidoComandaRepositoryUseCase;
    }

    @Override
    public List<Comanda> buscaComandaPorStatus(StatusComanda status) throws ExceptionMessage, Exception {
        status = StatusComanda.RECEBIDO;
        return buscarPorStatusComandaRepositoryUseCase.buscaComandaPorStatus(status).stream()
                .collect(Collectors.toList());
    }

}
