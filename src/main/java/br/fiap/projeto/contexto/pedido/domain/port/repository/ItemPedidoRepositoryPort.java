package br.fiap.projeto.contexto.pedido.domain.port.repository;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoRepositoryPort {

    ItemPedido criaItemPedido(ItemPedido itemPedido);
    ItemPedido buscaItemPedido(UUID codigo);
    List<ItemPedido> buscaTodos();
    ItemPedido atualizaItemPedido(ItemPedido itemPedido);
    void removeItemPedido(UUID codigo);
    Double calcularValorTotal();
    Integer calcularTempoTotalPreparo();
}