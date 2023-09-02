package br.fiap.projeto.contexto.pagamento.entity.integration;

import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PagamentoPedido {
 
	private String codigo;
	private Double valorTotal;

	public PagamentoPedido() {
	}

	public PagamentoPedido(String codigo, Double valorTotal) {
		this.codigo = codigo;
		this.valorTotal = valorTotal;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getValorTotal() {
		return valorTotal;
	}
}
 
