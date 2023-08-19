package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response;


import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

import java.util.Date;
import java.util.Objects;

public class PagamentoAprovadoDTO {

    private String codigoPedido;
    private Date dataPagamento;
    private StatusPagamento statusPagamento;

    public PagamentoAprovadoDTO(String codigoPedido, Date dataPagamento, StatusPagamento statusPagamento) {
        this.codigoPedido = codigoPedido;
        this.dataPagamento = dataPagamento;
        this.statusPagamento = statusPagamento;
    }

    public PagamentoAprovadoDTO(Pagamento pagamento){
        this.setCodigoPedido(pagamento.getCodigoPedido());
        this.setDataPagamento(pagamento.getDataPagamento());
        this.setStatusPagamento(pagamento.getStatus());
    }

    public PagamentoAprovadoDTO(PagamentoDTO pagamentoDTO) {
        this.setCodigoPedido(pagamentoDTO.getCodigoPedido());
        this.setDataPagamento(pagamentoDTO.getDataPagamento());
        this.setStatusPagamento(pagamentoDTO.getStatus());
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagamentoAprovadoDTO that = (PagamentoAprovadoDTO) o;
        return Objects.equals(getCodigoPedido(), that.getCodigoPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoPedido());
    }

    @Override
    public String toString() {
        return "PagamentoAprovadoDTO{" +
                "codigoPedido=" + codigoPedido +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
