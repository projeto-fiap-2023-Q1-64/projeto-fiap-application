package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

public class PagamentoNovoDTOResponse {

    private String codigoPedido;

    private StatusPagamento status;


    public PagamentoNovoDTOResponse(String codigoPedido, StatusPagamento status) {
        this.codigoPedido = codigoPedido;
        this.status = status;
    }

    //INFO usado no retorno da criação de um Novo Pagamento
    public PagamentoNovoDTOResponse(Pagamento pagamento) {
        this.setCodigoPedido(pagamento.getCodigoPedido());
        this.setStatus(pagamento.getStatus());
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
}
