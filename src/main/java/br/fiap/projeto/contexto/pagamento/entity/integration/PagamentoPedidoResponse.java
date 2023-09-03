package br.fiap.projeto.contexto.pagamento.entity.integration;

import java.time.LocalDateTime;

public class PagamentoPedidoResponse {
    private String codigoPedido;
    private String status;
    private LocalDateTime dataPagamento;

    public PagamentoPedidoResponse(String codigoPedido, String statusPagamento, LocalDateTime dataPagamento) {
        this.codigoPedido = codigoPedido;
        this.status = statusPagamento;
        this.dataPagamento = dataPagamento;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setStatus(String statusPagamento) {
        this.status = statusPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
}
