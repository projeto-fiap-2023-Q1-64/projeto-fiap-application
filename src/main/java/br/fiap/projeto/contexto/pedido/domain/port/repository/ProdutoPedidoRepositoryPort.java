package br.fiap.projeto.contexto.pedido.domain.port.repository;

import br.fiap.projeto.contexto.pedido.domain.ProdutoPedido;

import java.util.List;
import java.util.UUID;

public interface ProdutoPedidoRepositoryPort {

    ProdutoPedido criaProdutoPedido(ProdutoPedido produtoPedido);
    ProdutoPedido buscaProdutoPedido(UUID codigo);
    List<ProdutoPedido> buscaTodos();
    ProdutoPedido atualizaProdutoPedido(UUID codigo, ProdutoPedido produtoPedido);
    void removeProdutoPedido(UUID codigo);
}