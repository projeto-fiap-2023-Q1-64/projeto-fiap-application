package br.fiap.projeto.contexto.pedido.entity;

import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
 
	private UUID codigo;
	private List<ItemPedido> itens = new ArrayList<>();
	private UUID cliente;
	private StatusPedido status;
	private Double valorTotal;

	public Pedido() {
	}

	public Pedido(UUID codigo, List<ItemPedido> itens, UUID cliente, StatusPedido status, Double valorTotal) {
		this.codigo = codigo;
		this.itens = itens;
		this.cliente = cliente;
		this.status = status;
		this.valorTotal = valorTotal;
	}

	public UUID getCodigo() {
		return codigo;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public UUID getCliente() {
		return cliente;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public Pedido(UUID cliente){
		if(cliente != null) {
			this.cliente = cliente;
		}
		this.status = StatusPedido.INICIADO;
		this.valorTotal = 0d;
	}
	public void atualizarValorTotal(Double novoValor){
		this.valorTotal = novoValor;
	}
	public void adicionarItem(ItemPedido itemPedido) {
		this.itens.add(itemPedido);
	}
	public void atualizarStatus(StatusPedido status){
		this.status = status;
	}
}
 
