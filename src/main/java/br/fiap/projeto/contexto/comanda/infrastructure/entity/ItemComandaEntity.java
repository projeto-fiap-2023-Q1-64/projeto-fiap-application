package br.fiap.projeto.contexto.comanda.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import br.fiap.projeto.contexto.comanda.domain.ItemComandaChave;
import br.fiap.projeto.contexto.comanda.infrastructure.mapper.ComandaMapper;
import br.fiap.projeto.contexto.produto.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_comanda")

public class ItemComandaEntity {

    @EmbeddedId
    private ItemComandaChave chave;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codigoItem")
    @JoinColumn(name = "codigo_item")
    private ComandaEntity comanda;

    @Column(nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private int qtde;

    public ItemComandaEntity(ItemComandaEntity itemComandaEntity) {
        this.chave = itemComandaEntity.getChave();
        this.comanda = new ComandaEntity(itemComandaEntity.getComanda());
        this.produto = itemComandaEntity.getProduto();
        this.qtde = itemComandaEntity.getQtde();
    }

    public void atualizar(ItemComandaEntity itemComandaEntity) {
        this.chave = itemComandaEntity.getChave();
        this.comanda = new ComandaEntity(itemComandaEntity.getComanda());
        this.produto = itemComandaEntity.getProduto();
        this.qtde = itemComandaEntity.getQtde();
    }

    public ItemComanda toItemComanda() {
        return new ItemComanda(chave, comanda.getCodigoPedido(), produto, qtde, ComandaMapper.toEntity(comanda));
    }

}
