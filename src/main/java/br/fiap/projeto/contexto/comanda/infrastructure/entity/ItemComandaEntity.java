package br.fiap.projeto.contexto.comanda.infrastructure.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.fiap.projeto.contexto.comanda.domain.ItemComanda;
import br.fiap.projeto.contexto.comanda.domain.ItemComandaChave;
import br.fiap.projeto.contexto.produto.domain.Produto;

@Entity
@Table(name = "itemcomanda")

public class ItemComandaEntity {

    @EmbeddedId
    private ItemComandaChave chave;

    @Column(nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private int qtde;

    @ManyToOne
    @JoinColumn(name = "codigoPedido")

    private ComandaEntity comanda;

    public ItemComandaEntity() {

    }

    public ItemComandaEntity(UUID codigoPedido, ItemComanda itemComanda) {
        this.chave = new ItemComandaChave(codigoPedido, itemComanda.getCodigoProduto());
        this.produto = itemComanda.getProduto();
        this.qtde = itemComanda.getQtde();
    }

    public void atualizar(ItemComanda itemComanda) {
        this.chave = new ItemComandaChave(itemComanda.getCodigoPedido(), itemComanda.getCodigoProduto());
        this.produto = itemComanda.getProduto();
        this.qtde = itemComanda.getQtde();
    }

    public ItemComanda toitemComanda() {
        return new ItemComanda(this.chave.getCodigoComanda(), this.chave.getCodigoProduto(), produto, qtde);
    }

    public UUID getCodigoProduto() {
        return chave.getCodigoProduto();
    }

    public UUID getCodigoPedido() {
        return chave.getCodigoComanda();
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQtde() {
        return qtde;
    }

}
