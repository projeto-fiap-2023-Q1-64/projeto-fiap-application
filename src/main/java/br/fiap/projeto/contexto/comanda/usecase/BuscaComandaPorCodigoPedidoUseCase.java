package br.fiap.projeto.contexto.comanda.usecase;

import java.util.Optional;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaPorCodigoPedidoUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;

public class BuscaComandaPorCodigoPedidoUseCase implements IBuscaPorComandaPorCodigoPedidoUseCase {

    private final IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase;

    public BuscaComandaPorCodigoPedidoUseCase(
            IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase) {
        this.buscarPorComandaPorCodigoPedidoRepositoryUseCase = buscarPorComandaPorCodigoPedidoRepositoryUseCase;
    }

    @Override
    public Comanda buscaComandaPorCodigoPedido(UUID codigoPedido)
            throws EntradaInvalidaException, ComandaNaoEncontradaException {
        Optional<Comanda> comanda = buscarPorComandaPorCodigoPedidoRepositoryUseCase.buscar(codigoPedido);
        comanda.orElseThrow(() -> new ComandaNaoEncontradaException("Comanda não encontrada!"));
        comanda.get().validarDados();
        return comanda.get();
    }

}
