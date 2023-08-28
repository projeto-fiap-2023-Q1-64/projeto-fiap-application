package br.fiap.projeto.contexto.comanda.usecase;

import java.util.List;
import java.util.stream.Collectors;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryPortUseCase;

public class BuscaFinalizadoStatusComandaUseCase implements IBuscarPorStatusComandaRepositoryPortUseCase {

    private final IBuscarPorStatusComandaRepositoryPortUseCase buscarPorStatusComandaRepositoryPortUseCase;

    public BuscaFinalizadoStatusComandaUseCase(
            IBuscarPorStatusComandaRepositoryPortUseCase buscarPorStatusComandaRepositoryPortUseCase) {
        this.buscarPorStatusComandaRepositoryPortUseCase = buscarPorStatusComandaRepositoryPortUseCase;
    }

    @Override
    public List<Comanda> buscaComandaPorStatus(StatusComanda status) throws ExceptionMessage, Exception {
        status = StatusComanda.FINALIZADO;
        return buscarPorStatusComandaRepositoryPortUseCase.buscaComandaPorStatus(status)
                .stream()
                .collect(Collectors.toList());
    }

}
