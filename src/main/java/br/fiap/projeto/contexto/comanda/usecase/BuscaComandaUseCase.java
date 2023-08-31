package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaRepositoryUseCase;

import java.util.UUID;

public class BuscaComandaUseCase implements IBuscaPorComandaUseCase {

    private final IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase;

    public BuscaComandaUseCase(IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase) {
        this.buscarComandaRepositoryUseCase = buscarComandaRepositoryUseCase;
    }

    @Override
    public Comanda buscaComandaPorStatus(UUID codigoComanda) throws ExceptionMessage {
        return buscarComandaRepositoryUseCase.buscar(codigoComanda);
    }

}
