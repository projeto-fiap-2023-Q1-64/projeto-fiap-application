package br.fiap.projeto.contexto.comanda.usecase;

import java.util.Optional;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryPortUseCase;

public class BuscaComandaUseCase implements IBuscarComandaRepositoryPortUseCase {

    private final IBuscarComandaRepositoryPortUseCase buscarComandaRepositoryPortUseCase;

    public BuscaComandaUseCase(IBuscarComandaRepositoryPortUseCase buscarComandaRepositoryPortUseCase) {
        this.buscarComandaRepositoryPortUseCase = buscarComandaRepositoryPortUseCase;
    }

    @Override
    public Optional<Comanda> buscar(UUID codigoComanda) throws ExceptionMessage {
        return buscarComandaRepositoryPortUseCase.buscar(codigoComanda);
    }

}
