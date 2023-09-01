package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class PrepararComandaUseCase implements IAtualizarComandaUseCase {

    private final IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase;
    private final ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase;

    public PrepararComandaUseCase(IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase,
            ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase) {
        this.buscarComandaRepositoryUseCase = buscarComandaRepositoryUseCase;
        this.criarComandaRepositoryUseCase = criarComandaRepositoryUseCase;
    }

    @Override
    public Comanda finalizar(UUID codigoComanda) throws EntradaInvalidaException {
        Comanda comanda = this.buscar(codigoComanda);
        if (comanda.getStatus().equals(StatusComanda.RECEBIDO)) {
            comanda.atualizaStatus(StatusComanda.EM_PREPARACAO);
        } else {
            throw new EntradaInvalidaException(comanda.getStatus().toString());
        }
        return criarComandaRepositoryUseCase.criar(comanda);
    }

    private Comanda buscar(UUID codigoComanda) throws EntradaInvalidaException {
        Comanda comanda = buscarComandaRepositoryUseCase
                .buscar(codigoComanda);
        if (comanda.getCodigoComanda() == null) {
            throw new EntityNotFoundException("Comanda não encontrada!");
        }
        return comanda;
    }

}
