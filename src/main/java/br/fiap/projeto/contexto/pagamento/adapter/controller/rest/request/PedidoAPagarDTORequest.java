package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PedidoAPagarDTORequest {

    private String codigoPedido;
    private Double valorTotal;
    private Date dataPagamento;
    private List<Pedido> pedidos;

    public PedidoAPagarDTORequest() {
    }

    public PedidoAPagarDTORequest(String codigoPedido, Double valorTotal) {
        this.codigoPedido = codigoPedido;
        this.valorTotal = valorTotal;
    }

    public PedidoAPagarDTORequest(PagamentoDTORequest pagamentoDTO){
        this.setCodigoPedido(pagamentoDTO.getCodigoPedido());
        this.setDataPagamento(pagamentoDTO.getDataPagamento());
    }

    public PedidoAPagarDTORequest(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        for(Pedido pedido : pedidos){
            this.setCodigoPedido(pedido.getCodigo());
            this.setValorTotal(pedido.getValorTotal());
        }
    }
    public PedidoAPagarDTORequest(Pagamento pagamento){
        this.setCodigoPedido(pagamento.getCodigoPedido());
        this.setDataPagamento(pagamento.getDataPagamento());

    }

    //INFO usado na criação de um Novo Pagamento
    public Pagamento conversorDePedidoAPagarDTOParaPagamento(){
        return new Pagamento(codigoPedido, valorTotal);
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorTotal() {return valorTotal; }

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
        PedidoAPagarDTORequest that = (PedidoAPagarDTORequest) o;
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
                ", total=" + valorTotal +
                '}';
    }
}
