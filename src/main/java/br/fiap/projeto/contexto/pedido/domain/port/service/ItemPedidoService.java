package br.fiap.projeto.contexto.pedido.domain.port.service;

import br.fiap.projeto.contexto.pedido.domain.dto.ItemPedidoDTO;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoService {
    ItemPedidoDTO criaItemPedido(ItemPedidoDTO itemPedidoDTO);
    ItemPedidoDTO buscaItemPedido(UUID codigo);
    List<ItemPedidoDTO> buscaTodos();
    ItemPedidoDTO atualizaItemPedido(ItemPedidoDTO itemPedidoDTO);
    void removeItemPedido(UUID codigo);
    Double calcularValorTotal();
    Integer calcularTempoTotalPreparo();
}