package br.fiap.projeto.contexto.pagamento.application.rest.response;

import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PagamentoDTO {

    private UUID codigo;

    private Long codigoPedido;

    private StatusPagamento status;

    private Date dataPagamento;

    public PagamentoDTO(){}

    public PagamentoDTO(UUID codigo, Long codigoPedido, StatusPagamento status, Date dataPagamento) {
        this.codigo = codigo;
        this.status = status;
        this.codigoPedido = codigoPedido;
        this.dataPagamento = dataPagamento;
    }

    public PagamentoDTO(Pagamento pagamento){
        this.codigo = pagamento.getCodigo();
        this.codigoPedido = pagamento.getCodigoPedido();
        this.status = pagamento.getStatus();
        this.dataPagamento = pagamento.getDataPagamento();
    }

    public PagamentoDTO(CompraAPagarDTO compraAPagarDTO){
        this.codigoPedido = compraAPagarDTO.getCodigoPedido();
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
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
        PagamentoDTO that = (PagamentoDTO) o;
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
