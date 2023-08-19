package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response;

import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

import java.util.UUID;

public class PagamentoStatusDTO {

    private UUID codigo;

    private StatusPagamento status;

    public PagamentoStatusDTO(UUID codigo, StatusPagamento status) {
        this.codigo = codigo;
        this.status = status;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
}
