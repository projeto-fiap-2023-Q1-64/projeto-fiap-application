package br.fiap.projeto.contexto.pagamento.external.integration.port;

public class PagamentoPedidoResponseDTO {
    private String codigoPedido;
    private String statusPagamento;

    public PagamentoPedidoResponseDTO(String codigoPedido, String statusPagamento) {
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
