package br.fiap.projeto.contexto.pedido.domain.port.service;

import br.fiap.projeto.contexto.pedido.domain.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.domain.dto.ItemPedidoDTO;

import java.util.List;

public interface ItemPedidoService {
    ItemPedidoDTO criaItemPedido(ItemPedidoDTO itemPedidoDTO);
    ItemPedidoDTO buscaItemPedido(ItemPedidoCodigo codigo);
    List<ItemPedidoDTO> buscaTodos();
    ItemPedidoDTO atualizaItemPedido(ItemPedidoDTO itemPedidoDTO);
    void removeItemPedido(ItemPedidoCodigo codigo);
    Double calcularValorTotal(ItemPedidoCodigo codigo);
    Integer calcularTempoTotalPreparo(ItemPedidoCodigo codigo);
}