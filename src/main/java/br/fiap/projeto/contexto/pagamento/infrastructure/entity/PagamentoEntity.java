package br.fiap.projeto.contexto.pagamento.infrastructure.entity;

import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="Pagamento")
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigo;

    //TODO relacionamento OneToOne
    private Long codigoPedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
    private Date dataPagamento;

    public PagamentoEntity(UUID codigo, Long codigoPedido, StatusPagamento statusPagamento, Date dataPagamento) {
        this.codigo = codigo;
        this.codigoPedido = codigoPedido;
        this.statusPagamento = statusPagamento;
        this.dataPagamento = dataPagamento;
    }

    public PagamentoEntity() {}

    public PagamentoEntity(Pagamento pagamento) {
        this.codigo = pagamento.getCodigo();
        this.codigoPedido = pagamento.getCodigoPedido();
        this.statusPagamento = pagamento.getStatus();
        this.dataPagamento = pagamento.getDataPagamento();

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

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
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
        PagamentoEntity that = (PagamentoEntity) o;
        return Objects.equals(getCodigo(), that.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}
