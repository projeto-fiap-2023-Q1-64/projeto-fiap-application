package br.fiap.projeto.contexto.pedido.domain;

import java.util.List;

import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;

public class Pedido {
 
	private Long codigo;
	 
	private List<ItemPedido> itens;
	 
	private Long cliente;
	 
	private StatusPedido status;
	 
	private Double valorTotal;
	 
	public Double calcularValorTotal() {
		return null;
	}
	 
	public void adicionarProduto(ProdutoPedido produto) {
	 
	}
	 
	public void removerProduto(ProdutoPedido produto) {
	 
	}
	 
	public List<ItemPedido> listarItens() {
		return null;
	}
	 
	public void aumentarQuantidade(ProdutoPedido produto) {
	 
	}
	 
	public void reduzirQuantidade(ProdutoPedido produto) {
	 
	}
	 
	public Integer calcularTempoTotalPreparo() {
		return null;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
 
