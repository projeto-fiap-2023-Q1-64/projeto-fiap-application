package br.fiap.projeto.contexto.pagamento.entity.integration;

public class PagamentoPedidoResponse {
    private String codigoPedido;
    private String statusPagamento;

    public PagamentoPedidoResponse(String codigoPedido, String statusPagamento) {
        this.codigoPedido = codigoPedido;
        this.statusPagamento = statusPagamento;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

}
