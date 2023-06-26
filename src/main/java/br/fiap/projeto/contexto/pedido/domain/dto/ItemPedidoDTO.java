package br.fiap.projeto.contexto.pedido.domain.dto;

import br.fiap.projeto.contexto.pedido.domain.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.domain.ProdutoPedido;

public class ItemPedidoDTO {
    ItemPedidoCodigo codigo;
    private Pedido pedido;
    private ProdutoPedido produto;
    private Integer quantidade;
    private Double valorUnitario;
    public ItemPedidoDTO ( ItemPedidoCodigo codigo,
                           Pedido pedido,
                           ProdutoPedido produto,
                           Integer quantidade,
                           Double valorUnitario ){
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }
    public ItemPedidoCodigo getCodigo() {
        return codigo;
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
