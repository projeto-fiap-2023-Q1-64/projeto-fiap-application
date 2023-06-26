package br.fiap.projeto.contexto.pedido.domain.port.service;

import br.fiap.projeto.contexto.pedido.domain.dto.ProdutoPedidoDTO;

import java.util.List;
import java.util.UUID;

public interface ProdutoPedidoService {
    ProdutoPedidoDTO criaProdutoPedido(ProdutoPedidoDTO produtoPedidoDTO);
    ProdutoPedidoDTO buscaProdutoPedido(UUID codigo);
    List<ProdutoPedidoDTO> buscaTodos();
    ProdutoPedidoDTO atualizaProdutoPedido(UUID codigo, ProdutoPedidoDTO produtoPedidoDTO);
    void removeProdutoPedido(UUID codigo);
}
