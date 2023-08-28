package br.fiap.projeto.contexto.pedido.infrastructure.entity;

import br.fiap.projeto.contexto.pedido.domain.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.domain.enums.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_pedido")
public class ItemPedidoEntity {
    @EmbeddedId
    private ItemPedidoCodigo codigo;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pedidoCodigo")
    @JoinColumn(name = "pedido_codigo")
    private PedidoEntity pedido;
    @Column(nullable = false)
    private int quantidade;
    @Column(nullable = false)
    private String produtoNome;
    @Column(nullable = false)
    private String produtoDescricao;
    @Column(nullable = false, precision = 2)
    private double valorUnitario;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaProduto categoriaProduto;
    @Column(nullable = false)
    private String imagem;
    @Column(nullable = false)
    private Integer tempoPreparoMin;

    public ItemPedidoEntity(ItemPedidoEntity itemPedidoEntity) {

        this.codigo = itemPedidoEntity.getCodigo();
        this.pedido = new PedidoEntity(itemPedidoEntity.getPedido());
        this.quantidade = itemPedidoEntity.getQuantidade();
        this.produtoNome = itemPedidoEntity.getProdutoNome();
        this.produtoDescricao = itemPedidoEntity.getProdutoDescricao();
        this.valorUnitario = itemPedidoEntity.getValorUnitario();
        this.categoriaProduto = itemPedidoEntity.getCategoriaProduto();
        this.imagem = itemPedidoEntity.getImagem();
        this.tempoPreparoMin = itemPedidoEntity.getTempoPreparoMin();
    }

    public void atualizar(ItemPedidoEntity itemPedidoEntity) {
        this.codigo = itemPedidoEntity.getCodigo();
        this.pedido = new PedidoEntity(itemPedidoEntity.getPedido());
        this.quantidade = itemPedidoEntity.getQuantidade();
        this.produtoNome = itemPedidoEntity.getProdutoNome();
        this.produtoDescricao = itemPedidoEntity.getProdutoDescricao();
        this.valorUnitario = itemPedidoEntity.getValorUnitario();
        this.categoriaProduto = itemPedidoEntity.getCategoriaProduto();
        this.imagem = itemPedidoEntity.getImagem();
        this.tempoPreparoMin = itemPedidoEntity.getTempoPreparoMin();
    }
}