package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

import java.util.Date;
import java.util.Objects;

public class PagamentoAEnviarAoGatewayDTORequest {

    private String codigoPedido;
    private Double valorTotal;
    //TODO remover o Status da Request
    private StatusPagamento statusPagamento;
    private Date dataPagamento;

    public PagamentoAEnviarAoGatewayDTORequest() {
    }
    public PagamentoAEnviarAoGatewayDTORequest(Pagamento pagamento){
        this.setCodigoPedido(pagamento.getCodigoPedido());
        this.setValorTotal(pagamento.getValorTotal());
        this.setStatusPagamento(pagamento.getStatus());
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    //TODO validar a manutenção de enviar a Data ao Gateway - se possível remover
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
        PagamentoAEnviarAoGatewayDTORequest that = (PagamentoAEnviarAoGatewayDTORequest) o;
        return Objects.equals(getCodigoPedido(), that.getCodigoPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoPedido());
    }

    @Override
    public String toString() {
        return "PagamentoAEnviarAoGatewayDTORequest{" +
                "codigoPedido='" + codigoPedido + '\'' +
                ", valorTotal=" + valorTotal +
                ", statusPagamento=" + statusPagamento +
                '}';
    }
}
