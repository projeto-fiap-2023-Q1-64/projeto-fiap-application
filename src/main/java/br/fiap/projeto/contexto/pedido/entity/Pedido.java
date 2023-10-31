package br.fiap.projeto.contexto.pedido.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;

public class Pedido {

	private UUID codigo;
	private List<ItemPedido> itens = new ArrayList<>();
	private UUID cliente;
	private StatusPedido status;
	private Double valorTotal;
	private LocalDateTime dataCriacao;

	public Pedido() {
	}

	public Pedido(UUID codigo, List<ItemPedido> itens, UUID cliente, StatusPedido status, Double valorTotal,
			LocalDateTime dataCriacao) throws InvalidStatusException, NoItensException {
		this.codigo = codigo;
		this.itens = itens;
		this.cliente = cliente;
		this.status = status;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
		validaPedido();
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

	public Pedido(String cliente) {
		if (cliente != null && !cliente.isEmpty()) {
			UUID var = UUID.fromString(cliente);
			System.out.println(var);
			this.cliente = UUID.fromString(cliente);
			System.out.println(this.cliente);
		}
		this.status = StatusPedido.INICIADO;
		this.valorTotal = 0d;
		this.dataCriacao = LocalDateTime.now();
	}

	public void atualizarValorTotal(Double novoValor) {
		this.valorTotal = novoValor;
	}

	public void adicionarItem(ItemPedido itemPedido) {
		this.itens.add(itemPedido);
	}

	public void atualizarStatus(StatusPedido status) {
		this.status = status;
	}

	private void validaPedido() throws InvalidStatusException, NoItensException {

		if ((codigo == null) || (cliente == null) || (dataCriacao == null)) {
			throw new NoItensException("Status Nulo");
		}

		if ((valorTotal == null) || (valorTotal <= 0)) {
			throw new NoItensException("Status Nulo");
		}

		if (status.equals(null)) {
			throw new InvalidStatusException("Status Nulo");
		}
	}
}
