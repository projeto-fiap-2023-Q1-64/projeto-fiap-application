package br.fiap.projeto.contexto.pedido.usecase.port.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.usecase.exception.IntegrationProdutoException;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidOperacaoProdutoException;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.ItemNotFoundException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;

import java.util.UUID;

public interface IPedidoManagementUseCase {
    Pedido criaPedido(String codigoCliente) throws InvalidStatusException, NoItensException;

    Pedido adicionarProduto(UUID codigoPedido, UUID codigoProduto) throws InvalidOperacaoProdutoException,
            ItemNotFoundException, IntegrationProdutoException, InvalidStatusException, NoItensException;

    Pedido aumentarQuantidade(UUID codigoPedido, UUID codigoProduto)
            throws ItemNotFoundException, InvalidOperacaoProdutoException, InvalidStatusException, NoItensException;

    Pedido reduzirQuantidade(UUID codigoPedido, UUID codigoProduto)
            throws ItemNotFoundException, InvalidOperacaoProdutoException, InvalidStatusException, NoItensException;

    void removerProduto(UUID codigoPedido, UUID codigoProduto)
            throws InvalidOperacaoProdutoException, ItemNotFoundException, InvalidStatusException, NoItensException;
}
