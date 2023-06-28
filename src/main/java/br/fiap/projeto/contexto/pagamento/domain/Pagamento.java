package br.fiap.projeto.contexto.pagamento.domain;

import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;

import java.util.Date;

public class Pagamento {
 
	private Long codigoPedido;
	 
	private StatusPagamento status;
	 
	private Date dataPagamento;

	public Long getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(Long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
}
 
