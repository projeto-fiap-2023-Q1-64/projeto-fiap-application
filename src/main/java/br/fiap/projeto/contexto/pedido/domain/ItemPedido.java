package br.fiap.projeto.contexto.pedido.domain;

public class ItemPedido {
 
	private Pedido pedido;
	 
	private ProdutoPedido produto;
	 
	private Integer quantidade;
	 
	private Double valorUnitario;
	 
	public Double calcularValorTotal() {
		return null;
	}
	 
	public Integer calcularTempoTotalPreparo() {
		return null;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public ProdutoPedido getProduto() {
		return produto;
	}

	public void setProduto(ProdutoPedido produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
}
 
