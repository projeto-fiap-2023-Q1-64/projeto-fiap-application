package br.fiap.projeto.contexto.comanda.usecase;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.ICriarComandaPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryPortUseCase;

public class CriarComandaUseCase implements ICriarComandaPortUseCase {

    private final ICriarComandaRepositoryPortUseCase criarComandaRepositoryPortUseCase;

    public CriarComandaUseCase(ICriarComandaRepositoryPortUseCase criarComandaRepositoryPortUseCase) {
        this.criarComandaRepositoryPortUseCase = criarComandaRepositoryPortUseCase;
    }

    @Override
    public Comanda criarComanda(UUID codigoPedido) throws ExceptionMessage {
        return criarComandaRepositoryPortUseCase.criar(new Comanda(UUID.randomUUID(),
                codigoPedido,
                StatusComanda.RECEBIDO));
    }

}
