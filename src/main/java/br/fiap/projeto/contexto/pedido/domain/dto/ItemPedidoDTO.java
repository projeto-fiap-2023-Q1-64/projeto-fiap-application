package br.fiap.projeto.contexto.pedido.domain.dto;

import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.domain.ProdutoPedido;

public class ItemPedidoDTO {
    private Pedido pedido;
    private ProdutoPedido produto;
    private Integer quantidade;
    private Double valorUnitario;
    public ItemPedidoDTO ( Pedido pedido
                        , ProdutoPedido produto
                        , Integer quantidade
                        , Double valorUnitario ){
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }
    public ProdutoPedido getProduto() {
        return produto;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public Double getValorUnitario() {
        return valorUnitario;
    }
}
