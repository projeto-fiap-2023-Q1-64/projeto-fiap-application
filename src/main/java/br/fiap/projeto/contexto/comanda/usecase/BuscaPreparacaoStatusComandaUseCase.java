package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaPreparacaoStatusComandaUseCase implements IBuscaPorStatusPreparacaoComandaUseCase {

    private final IBuscarPorStatusPrepararComandaRepositoryUseCase buscarPorStatusPrepararComandaRepositoryUseCase;

    public BuscaPreparacaoStatusComandaUseCase(
            IBuscarPorStatusPrepararComandaRepositoryUseCase buscarPorStatusPrepararComandaRepositoryUseCase) {
        this.buscarPorStatusPrepararComandaRepositoryUseCase = buscarPorStatusPrepararComandaRepositoryUseCase;
    }

    @Override
    public List<Comanda> buscaComandaPorStatus(StatusComanda status) throws ExceptionMessage, Exception {
        status = StatusComanda.EM_PREPARACAO;
        return buscarPorStatusComandaRepositoryUseCase.buscaComandaPorStatus(status).stream()
                .collect(Collectors.toList());
    }

}
