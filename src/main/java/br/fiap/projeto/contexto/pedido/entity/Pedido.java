package br.fiap.projeto.contexto.pedido.entity;

import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
 
	private UUID codigo;
	private List<ItemPedido> itens = new ArrayList<>();
	private UUID cliente;
	private StatusPedido status;
	private Double valorTotal;
	private LocalDateTime dataCriacao;
	public Pedido() {
	}

	public Pedido(UUID codigo, List<ItemPedido> itens, UUID cliente, StatusPedido status, Double valorTotal, LocalDateTime dataCriacao) {
		this.codigo = codigo;
		this.itens = itens;
		this.cliente = cliente;
		this.status = status;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
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
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public Pedido(String cliente){
		if(cliente != null && !cliente.isEmpty()) {
			this.cliente = UUID.fromString(cliente);
		}
		this.status = StatusPedido.INICIADO;
		this.valorTotal = 0d;
		this.dataCriacao = LocalDateTime.now();
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
 
