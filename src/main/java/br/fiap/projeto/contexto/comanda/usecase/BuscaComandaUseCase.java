package br.fiap.projeto.contexto.comanda.usecase;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryUseCase;

public class BuscaComandaUseCase implements IBuscaPorComandaUseCase {

    private final IBuscarComandaRepositoryUseCase buscarComandaRepositoryUseCase;

    public BuscaComandaUseCase(IBuscarComandaRepositoryUseCase buscarComandaRepositoryUseCase) {
        this.buscarComandaRepositoryUseCase = buscarComandaRepositoryUseCase;
    }

    @Override
    public Comanda buscaComandaPorStatus(UUID codigoComanda) throws ExceptionMessage {
        return buscarComandaRepositoryUseCase.buscar(codigoComanda);
    }

}
