package br.fiap.projeto.contexto.comanda.infrastructure.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.fiap.projeto.contexto.comanda.domain.ProdutoComanda;

@Entity
@Table(name = "produtos_comandas")
public class ProdutoComandaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    public ProdutoComandaEntity(ProdutoComanda produtoComanda) {
        this.codigo = produtoComanda.getCodigo();
        this.nome = produtoComanda.getNome();
        this.descricao = produtoComanda.getDescricao();
    }

    public void atualizar(ProdutoComanda produto) {
        this.codigo = produto.getCodigo();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
    }

    public ProdutoComanda toProdutoComanda() {
        return new ProdutoComanda(codigo, nome, descricao);
    }

}
