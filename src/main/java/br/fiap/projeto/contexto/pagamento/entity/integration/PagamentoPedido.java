package br.fiap.projeto.contexto.pagamento.entity.integration;

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
 
