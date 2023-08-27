package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response;

import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;

public class PedidoAPagarDTOResponse {

    private String codigoPedido;
    private Double valorTotal;

    public PedidoAPagarDTOResponse(String codigoPedido, Double valorTotal) {
        this.codigoPedido = codigoPedido;
        this.valorTotal = valorTotal;
    }

    public PedidoAPagarDTOResponse(Pedido pedidoIntegration) {
        this.setCodigoPedido(pedidoIntegration.getCodigo());
        this.setValorTotal(pedidoIntegration.getValorTotal());
    }

    //TODO verificar as conversões necessárias para Pagamento

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
}
