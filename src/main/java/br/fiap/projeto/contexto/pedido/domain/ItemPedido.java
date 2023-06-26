package br.fiap.projeto.contexto.pedido.domain;

import br.fiap.projeto.contexto.pedido.domain.dto.ItemPedidoDTO;

public class ItemPedido {
	private ItemPedidoCodigo codigo;
	private Pedido pedido;
	private ProdutoPedido produto;
	private Integer quantidade;
	private Double valorUnitario;

	public ItemPedido (ItemPedidoCodigo codigo,
					   Pedido pedido,
					   ProdutoPedido produto,
					   Integer quantidade,
					   Double valorUnitario ){
		this.codigo = codigo;
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}
	public ItemPedido ( ItemPedidoDTO itemPedidoDTO ){
		this.pedido = itemPedidoDTO.getPedido();
		this.produto = itemPedidoDTO.getProduto();
		this.quantidade = itemPedidoDTO.getQuantidade();
		this.valorUnitario = itemPedidoDTO.getValorUnitario();
	}
	public Double calcularValorTotal() {
		return null;
	}
	public Integer calcularTempoTotalPreparo() {
		return null;
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
	public ItemPedidoDTO toItemPedidoDTO() {
		return new ItemPedidoDTO(this.codigo, this.pedido, this.produto, this.quantidade, this.valorUnitario);
	}
}