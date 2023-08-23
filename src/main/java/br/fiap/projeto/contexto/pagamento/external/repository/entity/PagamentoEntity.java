package br.fiap.projeto.contexto.pagamento.external.repository.entity;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="pagamentos")
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigo;

    private String codigoPedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
    private Date dataPagamento;

    private Double valorTotal;

    public PagamentoEntity(UUID codigo, String codigoPedido, StatusPagamento statusPagamento, Date dataPagamento, Double valorTotal) {
        this.codigo = codigo;
        this.codigoPedido = codigoPedido;
        this.statusPagamento = statusPagamento;
        this.dataPagamento = dataPagamento;
        this.valorTotal = valorTotal;
    }

    public PagamentoEntity() {}

    public PagamentoEntity(Pagamento pagamento) {
        this.setCodigo(pagamento.getCodigo());
        this.setCodigoPedido(pagamento.getCodigoPedido());
        this.setStatusPagamento(pagamento.getStatus());
        this.setDataPagamento(pagamento.getDataPagamento());
        this.setValorTotal(pagamento.getValorTotal());

    }

    /**
     * Teste de criação de pagamentos consumindo os pedidos que chegam da API
     * @param PedidoAPagarDTORequest
     */
    public PagamentoEntity(PedidoAPagarDTORequest pedidosAPagarDTORequest) {
        this.codigoPedido = pedidosAPagarDTORequest.getCodigoPedido();
        this.statusPagamento = StatusPagamento.PENDING;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
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
