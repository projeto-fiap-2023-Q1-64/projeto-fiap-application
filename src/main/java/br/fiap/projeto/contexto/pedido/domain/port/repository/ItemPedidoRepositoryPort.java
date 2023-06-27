package br.fiap.projeto.contexto.pedido.domain.port.repository;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.ItemPedidoCodigo;

import java.util.List;

public interface ItemPedidoRepositoryPort {

    ItemPedido criaItemPedido(ItemPedido itemPedido);
    ItemPedido buscaItemPedido(ItemPedidoCodigo codigo);
    List<ItemPedido> buscaTodos();
    ItemPedido atualizaItemPedido(ItemPedido itemPedido);
    void removeItemPedido(ItemPedidoCodigo codigo);
    Double calcularValorTotal();
    Integer calcularTempoTotalPreparo();
}