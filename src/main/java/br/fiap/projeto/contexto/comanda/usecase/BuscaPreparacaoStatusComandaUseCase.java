package br.fiap.projeto.contexto.comanda.usecase;

import java.util.List;
import java.util.stream.Collectors;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusPrepararComandaRepositoryUseCase;

public class BuscaPreparacaoStatusComandaUseCase implements IBuscaPorStatusComandaUseCase {

    private final IBuscarPorStatusPrepararComandaRepositoryUseCase buscarPorStatusPrepararComandaRepositoryUseCase;

    public BuscaPreparacaoStatusComandaUseCase(
            IBuscarPorStatusPrepararComandaRepositoryUseCase buscarPorStatusPrepararComandaRepositoryUseCase) {
        this.buscarPorStatusPrepararComandaRepositoryUseCase = buscarPorStatusPrepararComandaRepositoryUseCase;
    }

    @Override
    public List<Comanda> buscaComandaPorStatus(StatusComanda status) throws ExceptionMessage, Exception {
        status = StatusComanda.EM_PREPARACAO;
        return buscarPorStatusPrepararComandaRepositoryUseCase.buscaComandaPorStatus(status).stream()
                .collect(Collectors.toList());
    }

}
