package br.fiap.projeto.contexto.produto.external.repository.entity;

import br.fiap.projeto.contexto.produto.entity.Produto;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
@Table(name = "Produtos", uniqueConstraints = @UniqueConstraint(name = "UN_PRODUTO", columnNames = {"nome", "categoria"}))
public class ProdutoEntity {

    @Id
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 2)
    private Double preco;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    private String imagem;

    private Integer tempoPreparoMin;

    public ProdutoEntity() {
    }

    public ProdutoEntity(Produto produto) {
        this.codigo = produto.getCodigo();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.categoria = produto.getCategoria();
        this.imagem = produto.getImagem();
        this.tempoPreparoMin = produto.getTempoPreparoMin();
    }

    public Produto toProduto() {
        return new Produto(codigo, nome, descricao, preco, categoria, imagem, tempoPreparoMin);
    }
}


