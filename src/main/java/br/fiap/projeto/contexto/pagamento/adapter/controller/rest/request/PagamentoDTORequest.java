package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PagamentoDTORequest {

    private UUID codigo;

    private String codigoPedido;

    private StatusPagamento status;

    private Date dataPagamento;

    private Double valorTotal;

    public PagamentoDTORequest() {
    }

    public PagamentoDTORequest(UUID codigo, String codigoPedido, StatusPagamento status, Date dataPagamento, Double valorTotal) {
        this.codigo = codigo;
        this.codigoPedido = codigoPedido;
        this.status = status;
        this.dataPagamento = dataPagamento;
        this.valorTotal = valorTotal;
    }

    //INFO usado no atualizar Status com a resposta do Gateway de Pagamento
    public PagamentoDTORequest(PagamentoStatusDTORequest pagamentoStatusDTORequest) {
        this.setCodigoPedido(pagamentoStatusDTORequest.getCodigoPedido());
        this.setStatus(pagamentoStatusDTORequest.getStatus());
    }

    public Pagamento conversorDePagamentoDTORequestParaPagamento(){
        return new Pagamento(codigo, codigoPedido, status, dataPagamento, valorTotal);
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


    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagamentoDTORequest that = (PagamentoDTORequest) o;
        return Objects.equals(getCodigo(), that.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }

    @Override
    public String toString() {
        return "PagamentoDTORequest{" +
                "codigo=" + codigo +
                ", codigoPedido=" + codigoPedido +
                ", status=" + status +
                ", dataPagamento=" + dataPagamento +
                '}';
    }

    public void atualizaDadosRequest(PagamentoDTORequest pagamentoDTORequest, PagamentoDTOResponse pagamentoDTOStatusAtual) {
        pagamentoDTORequest.atualizaCodigoDoPagamento(pagamentoDTOStatusAtual.getCodigo());
        pagamentoDTORequest.atualizaValorTotal(pagamentoDTOStatusAtual.getValorTotal());
        pagamentoDTORequest.atualizaDataPagamento(pagamentoDTOStatusAtual.getDataPagamento());
    }
    private void atualizaCodigoDoPagamento(UUID codigo) {
       this.setCodigo(codigo);
    }
    private void atualizaValorTotal(Double valorTotal) {
        this.setValorTotal(valorTotal);
    }
    private void atualizaDataPagamento(Date dataPagamento) {
        this.setDataPagamento(dataPagamento);
    }
}
