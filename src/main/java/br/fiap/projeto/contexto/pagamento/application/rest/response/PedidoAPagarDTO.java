package br.fiap.projeto.contexto.pagamento.application.rest.response;

import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.infrastructure.entity.PagamentoEntity;
import br.fiap.projeto.contexto.pagamento.infrastructure.integration.port.Pedido;


import java.util.List;
import java.util.Objects;

public class PedidoAPagarDTO {

    private String codigoPedido;
    private Double total;

    private List<Pedido> pedidos;

    public PedidoAPagarDTO(String codigoPedido, Double total) {
        this.codigoPedido = codigoPedido;
        this.total = total;
    }

    public PedidoAPagarDTO(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        for(Pedido pedido : pedidos){
            this.setCodigoPedido(pedido.getCodigo());
            this.setTotal(pedido.getValorTotal());
        }
    }
    public  PedidoAPagarDTO(Pagamento pagamento){
        this.setCodigoPedido(pagamento.getCodigoPedido());

}

    public PedidoAPagarDTO(PagamentoEntity pagamentoEntity) {
        this.setCodigoPedido(pagamentoEntity.getCodigoPedido());
    }


    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
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
        PedidoAPagarDTO that = (PedidoAPagarDTO) o;
        return Objects.equals(getCodigoPedido(), that.getCodigoPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoPedido());
    }

    @Override
    public String toString() {
        return "PedidoAPagarDTO{" +
                "codigoPedido=" + codigoPedido +
                ", total=" + total +
                '}';
    }
}
