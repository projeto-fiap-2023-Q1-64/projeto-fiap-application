package br.fiap.projeto.contexto.pedido.domain.dto;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;

import java.util.List;
import java.util.UUID;

public class PedidoDTO {
    private UUID codigo;
    private List<ItemPedido> itens;
    private UUID cliente;
    private StatusPedido status;
    private Double valorTotal;
    public PedidoDTO (	UUID codigo
                    , List<ItemPedido> itens
                    , UUID cliente
                    , StatusPedido status
                    , Double valorTotal ){
        this.codigo = codigo;
        this.itens = itens;
        this.cliente = cliente;
        this.status = status;
        this.valorTotal = valorTotal;
    }

    public UUID getCodigo() {
        return codigo;
    }
    public List<ItemPedido> getItens() {
        return itens;
    }
    public UUID getCliente() {
        return cliente;
    }
    public StatusPedido getStatus() {
        return status;
    }
    public Double getValorTotal() {
        return valorTotal;
    }
}
