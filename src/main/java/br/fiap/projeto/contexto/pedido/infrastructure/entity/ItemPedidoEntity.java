package br.fiap.projeto.contexto.pedido.infrastructure.entity;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.Pedido;

import javax.persistence.*;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name="ItemPedido")
public class ItemPedidoEntity {
    @Id
    @GeneratedValue
    private UUID codigo;
    @ManyToOne
    @JoinColumn(name = "codigo")
    private PedidoEntity pedido;
    @ManyToOne
    @JoinColumn(name = "codigo")
    private ProdutoPedidoEntity produto;
    @Column(nullable = false)
    private int quantidade;
    @Column(nullable = false, precision = 2)
    private double valorUnitario;
    public UUID getCodigo() {
        return codigo;
    }
    public PedidoEntity getPedido() {
        return pedido;
    }
    public ProdutoPedidoEntity getProduto() {
        return produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public double getValorUnitario() {
        return valorUnitario;
    }
    public ItemPedidoEntity() {

    }
}