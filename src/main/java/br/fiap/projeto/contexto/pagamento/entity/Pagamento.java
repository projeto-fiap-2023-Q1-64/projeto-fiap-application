package br.fiap.projeto.contexto.pagamento.entity;

import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Pagamento {

	private UUID codigo;
	private String codigoPedido;
	private StatusPagamento status;
	private Date dataPagamento;
	private Double valorTotal;

	public Pagamento(UUID codigo, String codigoPedido, StatusPagamento status, Date dataPagamento, Double valorTotal) {
		this.codigo = codigo;
		this.codigoPedido = codigoPedido;
		this.status = status;
		this.dataPagamento = dataPagamento;
		this.valorTotal = valorTotal;
	}

	//INFO usado no conversor do PagamentoDTORequest e PedidoAPagarDTORequest
	public Pagamento(String codigoPedido,  Double valorTotal){
		if(codigoPedido.isEmpty()){
			throw new ResourceNotFoundException("Código de pedido inexistente");
		}
		this.codigoPedido = codigoPedido;
		this.status = StatusPagamento.PENDING;
		this.dataPagamento = new Date();
		this.valorTotal = valorTotal;
	}

	//INFO usado no conversor do PagamentoAEnviarAoGatewayDTORequest
	public Pagamento(String codigoPedido, Double valorTotal, StatusPagamento status, Date dataPagamento){
		this.codigoPedido = codigoPedido;
		this.valorTotal = valorTotal;
		this.dataPagamento = dataPagamento;
		this.status = status;
	}

	public UUID getCodigo() {
		return codigo;
	}
	public String getCodigoPedido() {
		return codigoPedido;
	}
	public StatusPagamento getStatus() {
		return status;
	}
	private void setStatus(StatusPagamento status) {
		this.status = status;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public Double getValorTotal() {
		return valorTotal;
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
 
