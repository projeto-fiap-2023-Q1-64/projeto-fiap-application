package br.fiap.projeto.contexto.comanda.usecase;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;

public class PrepararComandaUseCase implements IAtualizarComandaUseCase {

    private final IBuscarComandaRepositoryUseCase buscarComandaRepositoryUseCase;
    private final ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase;

    public PrepararComandaUseCase(IBuscarComandaRepositoryUseCase buscarComandaRepositoryUseCase,
            ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase) {
        this.buscarComandaRepositoryUseCase = buscarComandaRepositoryUseCase;
        this.criarComandaRepositoryUseCase = criarComandaRepositoryUseCase;
    }

    @Override
    public Comanda atualizar(UUID codigoComanda) throws ExceptionMessage {
        Comanda comanda = this.buscar(codigoComanda);
        if (comanda.getStatus().equals(StatusComanda.RECEBIDO)) {
            comanda.atualizaStatus(StatusComanda.EM_PREPARACAO);
        } else {
            throw new ExceptionMessage(comanda.getStatus().toString());
        }
        return criarComandaRepositoryUseCase.criar(comanda);
    }

    private Comanda buscar(UUID codigoComanda) throws ExceptionMessage {
        Comanda comanda = buscarComandaRepositoryUseCase
                .buscar(codigoComanda);
        if (comanda.getCodigoComanda() == null) {
            new EntityNotFoundException("Comanda não encontrada!");
        }

        return comanda;
    }

}
