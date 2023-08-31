package br.fiap.projeto.contexto.pedido.entity.integration;

import br.fiap.projeto.contexto.pedido.entity.enums.StatusPagamento;

import java.util.Date;

public class PagamentoPedido {
    private String codigoPedido;
    private Date dataPagamento;
    private StatusPagamento statusPagamento;

    public PagamentoPedido() {
    }

    public PagamentoPedido(String codigoPedido, Date dataPagamento, StatusPagamento statusPagamento) {
        this.codigoPedido = codigoPedido;
        this.dataPagamento = dataPagamento;
        this.statusPagamento = statusPagamento;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }
    public Boolean isPago(){
        return this.statusPagamento.getDescricao().equals(StatusPagamento.APPROVED.getDescricao());
    }
    public Boolean isCanceled(){
        return this.statusPagamento.getDescricao().equals(StatusPagamento.CANCELLED.getDescricao());
    }
}
