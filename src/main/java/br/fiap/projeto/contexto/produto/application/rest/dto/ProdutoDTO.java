package br.fiap.projeto.contexto.produto.application.rest.dto;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;

public class ProdutoDTO {

    private String codigo;

    private String nome;

    private String descricao;

    private Double preco;

    private String categoria;

    private String imagem;

    private Integer tempoPreparoMin;

    public ProdutoDTO(String codigo, String nome, String descricao, Double preco, String categoria, String imagem,
            Integer tempoPreparoMin) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.imagem = imagem;
        this.tempoPreparoMin = tempoPreparoMin;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public Integer getTempoPreparoMin() {
        return tempoPreparoMin;
    }

    public Produto toProduto() {
        return new Produto(codigo, nome, descricao, preco, CategoriaProduto.valueOf(categoria), imagem,
                tempoPreparoMin);
    }

    public static ProdutoDTO getInstance(Produto produto) {
        return new ProdutoDTO(produto.getCodigo(), produto.getNome(), produto.getDescricao(), produto.getPreco(),
                produto.getCategoria().name(), produto.getImagem(), produto.getTempoPreparoMin());
    }
}
