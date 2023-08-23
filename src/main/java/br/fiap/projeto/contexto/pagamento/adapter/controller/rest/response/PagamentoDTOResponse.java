package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PagamentoDTOResponse {

    private UUID codigo;

    private String codigoPedido;

    private StatusPagamento status;

    private Date dataPagamento;

    public PagamentoDTOResponse() {
    }

    public PagamentoDTOResponse(Pagamento pagamento){
        this.setCodigo(pagamento.getCodigo());
        this.setCodigoPedido(pagamento.getCodigoPedido());
        this.setStatus(pagamento.getStatus());
        this.setDataPagamento(pagamento.getDataPagamento());
    }

    //usado na criação de um novo pagamento
    public PagamentoDTOResponse(PagamentoDTORequest pagamentoDTORequest){
        this.setCodigo(pagamentoDTORequest.getCodigo());
        this.setCodigoPedido(pagamentoDTORequest.getCodigoPedido());
        this.setDataPagamento(pagamentoDTORequest.getDataPagamento());
        this.setStatus(pagamentoDTORequest.getStatus());
    }

    public UUID getCodigo() {
        return codigo;
    }

    private void setCodigo(UUID codigo) {
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
        PagamentoDTOResponse that = (PagamentoDTOResponse) o;
        return Objects.equals(getCodigo(), that.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }

    @Override
    public String toString() {
        return "PagamentoDTO{" +
                "codigo=" + codigo +
                ", codigoPedido=" + codigoPedido +
                ", status=" + status +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
