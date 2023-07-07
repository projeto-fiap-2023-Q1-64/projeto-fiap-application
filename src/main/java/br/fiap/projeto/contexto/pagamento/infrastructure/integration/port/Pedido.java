package br.fiap.projeto.contexto.pagamento.infrastructure.integration.port;

import java.util.Objects;

public class Pedido {

    private String codigo;
    private Double valorTotal;


    public Pedido(String codigo, Double valorTotal) {
        this.codigo = codigo;
        this.valorTotal = valorTotal;
    }

    public String getCodigo() {
        return codigo;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigo=" + codigo +
                ", valorTotal=" + valorTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(getCodigo(), pedido.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}
