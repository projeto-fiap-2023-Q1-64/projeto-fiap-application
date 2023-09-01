package br.fiap.projeto.contexto.comanda.usecase;

import java.util.Optional;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.ICriarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;

public class CriarComandaUseCase implements ICriarComandaUseCase {

    private final ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase;
    private final IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase;

    public CriarComandaUseCase(ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase,
            IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase) {
        this.criarComandaRepositoryUseCase = criarComandaRepositoryUseCase;
        this.buscarPorComandaPorCodigoPedidoRepositoryUseCase = buscarPorComandaPorCodigoPedidoRepositoryUseCase;
    }

    @Override
    public Comanda criarComanda(UUID codigoPedido) throws EntradaInvalidaException {
        if (codigoPedido == null) {
            throw new EntradaInvalidaException("Código de pedido inválido!");
        }
        Optional<Comanda> comanda = buscarPorComandaPorCodigoPedidoRepositoryUseCase.buscar(codigoPedido);
        if (comanda.empty() != null) {
            new ComandaNaoEncontradaException("Comanda já existe");
        }

        return criarComandaRepositoryUseCase.criar(new Comanda(UUID.randomUUID(),
                codigoPedido,
                StatusComanda.RECEBIDO));
    }

}
