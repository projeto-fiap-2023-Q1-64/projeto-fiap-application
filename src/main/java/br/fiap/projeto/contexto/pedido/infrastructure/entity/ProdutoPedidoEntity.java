package br.fiap.projeto.contexto.pedido.infrastructure.entity;

import br.fiap.projeto.contexto.pedido.domain.ProdutoPedido;
import br.fiap.projeto.contexto.pedido.domain.enums.CategoriaProduto;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Produto")
public class ProdutoPedidoEntity {
    @Id
    @GeneratedValue
    private UUID codigo;
    private String nome;
    private String descricao;
    private double preco;
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;
    private String imagem;
    private int tempoPreparoMin;
    public ProdutoPedidoEntity(ProdutoPedido produtoPedido){
        this.codigo = produtoPedido.getCodigo();
        this.nome = produtoPedido.getNome();
        this.descricao = produtoPedido.getDescricao();
        this.preco = produtoPedido.getPreco();
        this.categoria = produtoPedido.getCategoria();
        this.imagem = produtoPedido.getImagem();
        this.tempoPreparoMin = produtoPedido.getTempoPreparoMin();
    }
    public ProdutoPedidoEntity() {
    }
    public void atualizar(ProdutoPedido produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.categoria = produto.getCategoria();
        this.imagem = produto.getImagem();
        this.tempoPreparoMin = produto.getTempoPreparoMin();
    }
    public ProdutoPedido toDomain() {
        return new ProdutoPedido(codigo, nome, descricao, preco, categoria, imagem, tempoPreparoMin);
    }
    public UUID getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public double getPreco() {
        return preco;
    }
    public CategoriaProduto getCategoria() {
        return categoria;
    }
    public String getImagem() {
        return imagem;
    }
    public int getTempoPreparoMin() {
        return tempoPreparoMin;
    }
}