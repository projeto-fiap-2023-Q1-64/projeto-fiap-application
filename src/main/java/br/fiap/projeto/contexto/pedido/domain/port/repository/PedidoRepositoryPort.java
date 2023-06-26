package br.fiap.projeto.contexto.pedido.domain.port.repository;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.domain.ProdutoPedido;

import java.util.List;
import java.util.UUID;

public interface PedidoRepositoryPort {

    Pedido criaPedido(Pedido pedido);
    Pedido buscaPedido(UUID codigo);
    List<Pedido> buscaTodos();
    Pedido atualizaPedido(UUID codigo, Pedido pedido);
    void removePedido(UUID codigo);
    Double calcularValorTotal();
    void aumentarQuantidade(ProdutoPedido produto);
    void reduzirQuantidade(ProdutoPedido produto);
    Integer calcularTempoTotalPreparo();
    void adicionarProduto(ProdutoPedido produto);
    void removerProduto(ProdutoPedido produto);
    List<ItemPedido> listarItens();
}