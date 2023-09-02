package br.fiap.projeto.contexto.comanda.usecase;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaDuplicadaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.ICriarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.ICriarComandaRepositoryUseCase;

public class CriarComandaUseCase implements ICriarComandaUseCase {

    private final ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase;
    private final IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase;

    public CriarComandaUseCase(ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase,
            IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase) {
        this.criarComandaRepositoryUseCase = criarComandaRepositoryUseCase;
        this.buscarPorComandaPorCodigoPedidoRepositoryUseCase = buscarPorComandaPorCodigoPedidoRepositoryUseCase;
    }

    @Override
    public Comanda criarComanda(UUID codigoPedido) throws EntradaInvalidaException, ComandaDuplicadaException {
        if (codigoPedido == null) {
            throw new EntradaInvalidaException("Código de pedido inválido!");
        }
        if (buscarPorComandaPorCodigoPedidoRepositoryUseCase.buscar(codigoPedido).isPresent()) {
            throw new ComandaDuplicadaException("Esta comanda já existe");
        }

        return criarComandaRepositoryUseCase.criar(new Comanda(UUID.randomUUID(),
                codigoPedido,
                StatusComanda.RECEBIDO));
    }

}
