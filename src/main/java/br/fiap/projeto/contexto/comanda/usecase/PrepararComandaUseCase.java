package br.fiap.projeto.contexto.comanda.usecase;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryPortUseCase;

public class PrepararComandaUseCase implements IAtualizarComandaPortUseCase {

    private final IBuscarComandaRepositoryPortUseCase buscarComandaRepositoryPortUseCase;
    private final ICriarComandaRepositoryPortUseCase criarComandaRepositoryPortUseCase;

    public PrepararComandaUseCase(IBuscarComandaRepositoryPortUseCase buscarComandaRepositoryPortUseCase,
            ICriarComandaRepositoryPortUseCase criarComandaRepositoryPortUseCase) {
        this.buscarComandaRepositoryPortUseCase = buscarComandaRepositoryPortUseCase;
        this.criarComandaRepositoryPortUseCase = criarComandaRepositoryPortUseCase;
    }

    @Override
    public Comanda atualizar(UUID codigoComanda) throws ExceptionMessage {
        Comanda comanda = this.buscar(codigoComanda);
        if (comanda.getStatus().equals(StatusComanda.RECEBIDO)) {
            comanda.atualizaStatus(StatusComanda.EM_PREPARACAO);
        } else {
            throw new ExceptionMessage(comanda.getStatus().toString());
        }
        return criarComandaRepositoryPortUseCase.criar(comanda);
    }

    private Comanda buscar(UUID codigoComanda) throws ExceptionMessage {
        Optional<Comanda> optionalComanda = buscarComandaRepositoryPortUseCase.buscar(codigoComanda);
        optionalComanda.orElseThrow(() -> new EntityNotFoundException("Comanda não encontrada!"));
        return optionalComanda.get();
    }

}
