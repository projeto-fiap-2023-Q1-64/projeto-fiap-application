package br.fiap.projeto.contexto.pedido.infrastructure.entity;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.produto.infrastructure.entity.ProdutoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name="ItemPedido")
public class ItemPedidoEntity {
    @EmbeddedId
    private ItemPedidoCodigo codigo;
    @ManyToOne
    @MapsId("pedidoCodigo")
    @JoinColumn(name = "pedido_codigo")
    private PedidoEntity pedido;
    @ManyToOne
    @MapsId("produtoCodigo")
    @JoinColumn(name = "produto_codigo")
    private ProdutoPedidoEntity produto;
    @Column(nullable = false)
    private int quantidade;
    @Column(nullable = false, precision = 2)
    private double valorUnitario;
    public ItemPedidoEntity(ItemPedidoCodigo codigo,
                            PedidoEntity pedido,
                            ProdutoPedidoEntity produto,
                            int quantidade,
                            double valorUnitario){
        this.codigo = codigo;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public ItemPedidoCodigo getCodigo() {
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