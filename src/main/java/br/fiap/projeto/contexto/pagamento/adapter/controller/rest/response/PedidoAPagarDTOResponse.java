package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response;


import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

import java.util.Date;
import java.util.Objects;

public class PedidoAPagarDTOResponse {

    private String codigoPedido;
    private Date dataPagamento;
    private StatusPagamento statusPagamento;

    public PedidoAPagarDTOResponse() {
    }

    public PedidoAPagarDTOResponse(String codigoPedido, Date dataPagamento, StatusPagamento statusPagamento) {
        this.codigoPedido = codigoPedido;
        this.dataPagamento = dataPagamento;
        this.statusPagamento = statusPagamento;
    }

    public PedidoAPagarDTOResponse(Pagamento pagamento){
        this.setCodigoPedido(pagamento.getCodigoPedido());
        this.setDataPagamento(pagamento.getDataPagamento());
        this.setStatusPagamento(pagamento.getStatus());
    }

    public PedidoAPagarDTOResponse(PagamentoDTORequest pagamentoDTO) {
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
        PedidoAPagarDTOResponse that = (PedidoAPagarDTOResponse) o;
        return Objects.equals(getCodigoPedido(), that.getCodigoPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoPedido());
    }

    @Override
    public String toString() {
        return "PedidoAPagarDTOResponse{" +
                "codigoPedido=" + codigoPedido +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
