package br.fiap.projeto.contexto.pagamento.domain;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PedidoAPagarDTO;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.infrastructure.entity.PagamentoEntity;

public class Pagamento {

	private UUID codigo;
	private String codigoPedido;
	 
	private StatusPagamento status;
	 
	private Date dataPagamento;

	public Pagamento(UUID codigo, String codigoPedido, StatusPagamento status, Date dataPagamento) {
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

	public Pagamento(PedidoAPagarDTO pedidosAPagarDTO) {
		this.setCodigoPedido(pedidosAPagarDTO.getCodigoPedido());
	}


	public UUID getCodigo() {
		return codigo;
	}

	public void setCodigo(UUID codigo) {
		this.codigo = codigo;
	}

	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
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

	public void colocaEmProcessamento() {
		this.setStatus(StatusPagamento.IN_PROCESS);
		System.out.println("Notifica pedido: " + this.getCodigoPedido() + ", pagamento está em processamento.");
	}

	public void aprovaPagamento() {
		this.setStatus(StatusPagamento.APPROVED);
		System.out.println("Notifica aprovação do pagamento do pedido: " + this.getCodigoPedido());
	}
	public void cancelaPagamento() {
		this.setStatus(StatusPagamento.CANCELLED);
		System.out.println("Notifica cancelamento do pagamento do pedido: " + this.getCodigoPedido());
	}

	public void rejeitaPagamento() {
		this.setStatus(StatusPagamento.REJECTED);
		System.out.println("Notifica rejeição do pagamento do pedido: " + this.getCodigoPedido());
	}

	public boolean podeSerProcessado(StatusPagamento statusAtual, StatusPagamento statusRequest) {
		return statusAtual.equals(StatusPagamento.PENDING) && statusRequest.equals(StatusPagamento.IN_PROCESS);
	}

	public boolean podeSerAprovado(StatusPagamento statusAtual, StatusPagamento statusRequest) {
		return statusAtual.equals(StatusPagamento.IN_PROCESS) && statusRequest.equals(StatusPagamento.APPROVED);
	}

	public boolean podeSerCancelado(StatusPagamento statusAtual, StatusPagamento statusRequest) {
		return statusAtual.equals(StatusPagamento.IN_PROCESS) && statusRequest.equals(StatusPagamento.CANCELLED);
	}

	public boolean podeSerRejeitado(StatusPagamento statusAtual, StatusPagamento statusRequest) {
		return statusAtual.equals(StatusPagamento.IN_PROCESS) && statusRequest.equals(StatusPagamento.REJECTED);
	}

}
 
