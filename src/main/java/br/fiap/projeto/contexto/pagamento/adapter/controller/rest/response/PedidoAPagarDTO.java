package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;


import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PedidoAPagarDTO {

    private String codigoPedido;
    private Double total;
    private StatusPagamento statusPagamento;
    private Date dataPagamento;
    private List<Pedido> pedidos;

    public PedidoAPagarDTO(String codigoPedido, Double total) {
        this.codigoPedido = codigoPedido;
        this.total = total;

    }

    public PedidoAPagarDTO(PagamentoDTO pagamentoDTO){
        this.setCodigoPedido(pagamentoDTO.getCodigoPedido());
        this.setDataPagamento(pagamentoDTO.getDataPagamento());
        this.setStatusPagamento(pagamentoDTO.getStatus());
    }

    public PedidoAPagarDTO(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        for(Pedido pedido : pedidos){
            this.setCodigoPedido(pedido.getCodigo());
            this.setTotal(pedido.getValorTotal());
        }
    }
    public  PedidoAPagarDTO(Pagamento pagamento){
        this.setCodigoPedido(pagamento.getCodigoPedido());
        this.setStatusPagamento(pagamento.getStatus());
        this.setDataPagamento(pagamento.getDataPagamento());

    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento){this.statusPagamento = statusPagamento;}

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
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
        PedidoAPagarDTO that = (PedidoAPagarDTO) o;
        return Objects.equals(getCodigoPedido(), that.getCodigoPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoPedido());
    }

    @Override
    public String toString() {
        return "PedidoAPagarDTO{" +
                "codigoPedido=" + codigoPedido +
                ", total=" + total +
                '}';
    }
}
