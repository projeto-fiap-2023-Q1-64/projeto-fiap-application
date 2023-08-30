package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.ICriarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;

import java.util.UUID;

public class CriarComandaUseCase implements ICriarComandaUseCase {

    private final ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase;

    public CriarComandaUseCase(ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase) {
        this.criarComandaRepositoryUseCase = criarComandaRepositoryUseCase;
    }

    @Override
    public Comanda criarComanda(UUID codigoPedido) throws ExceptionMessage {
        return criarComandaRepositoryUseCase.criar(new Comanda(UUID.randomUUID(),
                codigoPedido,
                StatusComanda.RECEBIDO));
    }

}
