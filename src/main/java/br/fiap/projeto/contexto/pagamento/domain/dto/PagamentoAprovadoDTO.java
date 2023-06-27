package br.fiap.projeto.contexto.pagamento.domain.dto;


import br.fiap.projeto.contexto.pagamento.domain.Pagamento;

import java.util.Date;
import java.util.Objects;

public class PagamentoAprovadoDTO {

    private Long codigoPedido;
    private Date dataPagamento;


    public PagamentoAprovadoDTO(Long codigoPedido, Date dataPagamento) {
        this.codigoPedido = codigoPedido;
        this.dataPagamento = dataPagamento;
    }

    public PagamentoAprovadoDTO(Pagamento pagamento){
        setCodigoPedido(pagamento.getCodigoPedido());
        setDataPagamento(pagamento.getDataPagamento());
    }

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
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
        PagamentoAprovadoDTO that = (PagamentoAprovadoDTO) o;
        return Objects.equals(getCodigoPedido(), that.getCodigoPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoPedido());
    }

    @Override
    public String toString() {
        return "PagamentoAprovadoDTO{" +
                "codigoPedido=" + codigoPedido +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
