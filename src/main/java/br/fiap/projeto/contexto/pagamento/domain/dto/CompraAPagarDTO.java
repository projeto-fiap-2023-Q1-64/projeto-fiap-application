package br.fiap.projeto.contexto.pagamento.domain.dto;

import java.util.Objects;

public class CompraAPagarDTO {

    private Long codigoPedido;
    private Double total;

    public CompraAPagarDTO(Long codigoPedido, Double total) {
        this.codigoPedido = codigoPedido;
        this.total = total;
    }


    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompraAPagarDTO that = (CompraAPagarDTO) o;
        return Objects.equals(getCodigoPedido(), that.getCodigoPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoPedido());
    }

    @Override
    public String toString() {
        return "CompraAPagarDTO{" +
                "codigoPedido=" + codigoPedido +
                ", total=" + total +
                '}';
    }
}
