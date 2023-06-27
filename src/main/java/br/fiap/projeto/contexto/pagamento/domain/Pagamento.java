package br.fiap.projeto.contexto.pagamento.domain;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import br.fiap.projeto.contexto.pagamento.domain.dto.PagamentoDTO;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.infrastructure.entity.PagamentoEntity;

public class Pagamento {

	private UUID codigo;
	private Long codigoPedido;
	 
	private StatusPagamento status;
	 
	private Date dataPagamento;

	public Pagamento(UUID codigo, Long codigoPedido, StatusPagamento status, Date dataPagamento) {
		this.codigo = codigo;
		this.codigoPedido = codigoPedido;
		this.status = status;
		this.dataPagamento = dataPagamento;
	}

	public Pagamento(PagamentoDTO pagamentoDTO){
		this.setCodigo(pagamentoDTO.getCodigo());
		this.setCodigoPedido(pagamentoDTO.getCodigoPedido());
		this.setDataPagamento(pagamentoDTO.getDataPagamento());
		this.setStatus(pagamentoDTO.getStatus());

	}

	public Pagamento(PagamentoEntity pagamentoEntity){
		this.setCodigo(pagamentoEntity.getCodigo());
		this.setCodigoPedido(pagamentoEntity.getCodigoPedido());
		this.setDataPagamento(pagamentoEntity.getDataPagamento());
		this.setStatus(pagamentoEntity.getStatusPagamento());
	}

	public UUID getCodigo() {
		return codigo;
	}

	public void setCodigo(UUID codigo) {
		this.codigo = codigo;
	}

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
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pagamento pagamento = (Pagamento) o;
		return Objects.equals(getCodigo(), pagamento.getCodigo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCodigo());
	}

	@Override
	public String toString() {
		return "Pagamento{" +
				"codigo=" + codigo +
				", codigoPedido=" + codigoPedido +
				", status=" + status +
				", dataPagamento=" + dataPagamento +
				'}';
	}
}
 
