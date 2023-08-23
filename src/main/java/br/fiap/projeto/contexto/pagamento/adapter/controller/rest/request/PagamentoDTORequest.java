package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request;

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

    public PagamentoDTORequest() {
    }

    //Criação de um novo Pagamento: requer o código do pedido
    public PagamentoDTORequest(String codigoPedido) {
        this.status = StatusPagamento.PENDING;
        if(!(codigoPedido == null)){
            this.codigoPedido = codigoPedido;
        }else{
            throw new ResourceNotFoundException("Código de pedido inexistente");
        }
        this.dataPagamento = new Date();
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


}
