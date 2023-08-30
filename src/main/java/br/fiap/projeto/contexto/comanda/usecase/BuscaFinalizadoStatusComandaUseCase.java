package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusFinalizadoComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaFinalizadoStatusComandaUseCase implements IBuscaPorStatusFinalizadoComandaUseCase {

    private final IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase;

    public BuscaFinalizadoStatusComandaUseCase(
            IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase) {
        this.buscarPorStatusComandaRepositoryUseCase = buscarPorStatusComandaRepositoryUseCase;
    }

    @Override
    public List<Comanda> buscaComandaPorStatusFinalizado() throws ExceptionMessage, Exception {
        return buscarPorStatusComandaRepositoryUseCase.buscaComandaPorStatus(StatusComanda.FINALIZADO)
                .stream()
                .collect(Collectors.toList());
    }

}
