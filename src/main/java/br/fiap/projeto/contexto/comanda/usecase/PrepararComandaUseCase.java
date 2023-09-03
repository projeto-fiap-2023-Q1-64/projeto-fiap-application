package br.fiap.projeto.contexto.comanda.usecase;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;

public class PrepararComandaUseCase implements IAtualizarComandaUseCase {

    private final IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase;
    private final IAtualizarComandaRepositoryUseCase prepararComandaRepositoryUseCase;

    public PrepararComandaUseCase(
            IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase,
            IAtualizarComandaRepositoryUseCase prepararComandaRepositoryUseCase) {
        this.buscarPorComandaPorCodigoPedidoRepositoryUseCase = buscarPorComandaPorCodigoPedidoRepositoryUseCase;
        this.prepararComandaRepositoryUseCase = prepararComandaRepositoryUseCase;
    }

    @Override
    public Comanda alterarStatus(UUID codigoPedido) throws EntradaInvalidaException {
        Comanda comanda = this.buscar(codigoPedido);
        if (comanda.getStatus().equals(StatusComanda.RECEBIDO)) {
            comanda.atualizaStatus(StatusComanda.EM_PREPARACAO);
        } else {
            throw new EntradaInvalidaException(
                    "Status da comanda inválido para esta operação! Precisa estar em RECEBIDO.");
        }
        return prepararComandaRepositoryUseCase.atualizar(comanda);
    }

    private Comanda buscar(UUID codigoPedido) throws EntradaInvalidaException {
        Optional<Comanda> comanda = buscarPorComandaPorCodigoPedidoRepositoryUseCase.buscar(codigoPedido);
        comanda.orElseThrow(() -> new EntityNotFoundException("Comanda não encontrada!"));
        return comanda.get();
    }

}
