package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaRepositoryUseCase;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

public class PrepararComandaUseCase implements IAtualizarComandaUseCase {

    private final IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase;
    private final IAtualizarComandaRepositoryUseCase prepararComandaRepositoryUseCase;

    public PrepararComandaUseCase(IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase,
            IAtualizarComandaRepositoryUseCase prepararComandaRepositoryUseCase) {
        this.buscarComandaRepositoryUseCase = buscarComandaRepositoryUseCase;
        this.prepararComandaRepositoryUseCase = prepararComandaRepositoryUseCase;
    }

    @Override
    public Comanda alterarStatus(UUID codigoComanda) throws EntradaInvalidaException {
        Comanda comanda = this.buscar(codigoComanda);
        if (comanda.getStatus().equals(StatusComanda.RECEBIDO)) {
            comanda.atualizaStatus(StatusComanda.EM_PREPARACAO);
        } else {
            throw new EntradaInvalidaException(
                    "Status da comanda inválido para esta operação! Precisa estar em RECEBIDO.");
        }
        return prepararComandaRepositoryUseCase.atualizar(comanda);
    }

    private Comanda buscar(UUID codigoComanda) throws EntradaInvalidaException {
        Optional<Comanda> comanda = buscarComandaRepositoryUseCase.buscar(codigoComanda);
        comanda.orElseThrow(() -> new EntityNotFoundException("Comanda não encontrada!"));
        return comanda.get();
    }

}
