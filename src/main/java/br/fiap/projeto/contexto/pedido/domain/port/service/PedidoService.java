package br.fiap.projeto.contexto.pedido.domain.port.service;

import br.fiap.projeto.contexto.pedido.domain.dto.ItemPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.ProdutoPedidoDTO;

import java.util.List;
import java.util.UUID;

public interface PedidoService {
    PedidoDTO criaPedido(PedidoDTO pedidoDTO);
    PedidoDTO buscaPedido(UUID codigo);
    List<PedidoDTO> buscaTodos();
    PedidoDTO atualizaPedido(UUID codigo, PedidoDTO pedidoDTO);
    void removePedido(UUID codigo);
    Double calcularValorTotal();
    void aumentarQuantidade(ProdutoPedidoDTO produtoDTO);
    void reduzirQuantidade(ProdutoPedidoDTO produtoDTO);
    Integer calcularTempoTotalPreparo();
    void adicionarProduto(ProdutoPedidoDTO produtoDTO);
    void removerProduto(ProdutoPedidoDTO produtoDTO);
    List<ItemPedidoDTO> listarItens();
}
