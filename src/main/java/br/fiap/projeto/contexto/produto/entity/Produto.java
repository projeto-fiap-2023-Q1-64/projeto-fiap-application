package br.fiap.projeto.contexto.produto.entity;

import br.fiap.projeto.contexto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;

public class Produto {

    private String codigo;

    private String nome;

    private String descricao;

    private Double preco;

    private CategoriaProduto categoria;

    private String imagem;

    private Integer tempoPreparoMin;

    public Produto(String nome, String descricao, Double preco, CategoriaProduto categoria, String imagem,
            Integer tempoPreparoMin) throws EntradaInvalidaException {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.imagem = imagem;
        this.tempoPreparoMin = tempoPreparoMin;
        validarProdutoSemCodigo();
    }

    public Produto(String codigo, String nome, String descricao, Double preco, CategoriaProduto categoria,
            String imagem, Integer tempoPreparoMin) throws EntradaInvalidaException {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.imagem = imagem;
        this.tempoPreparoMin = tempoPreparoMin;
        validarProdutoComCodigo();
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

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public Integer getTempoPreparoMin() {
        return tempoPreparoMin;
    }

    private void validarProdutoComCodigo() throws EntradaInvalidaException {
        if ((codigo == null) || (nome == null) || (descricao == null) || (preco == null) || (tempoPreparoMin == null)
                || (tempoPreparoMin <= 0)) {
            throw new EntradaInvalidaException("Entrada inválida! Produto não deve ser nulo!");
        }
        if (categoria.equals(null)) {
            throw new EntradaInvalidaException("Entrada inválida! Produto não deve ser nulo!");
        }
    }

    private void validarProdutoSemCodigo() throws EntradaInvalidaException {
        if ((nome == null) || (descricao == null) || (preco == null) || (tempoPreparoMin == null)
                || (tempoPreparoMin <= 0)) {
            throw new EntradaInvalidaException("Entrada inválida! Produto não deve ser nulo!");
        }
        if (categoria.equals(null)) {
            throw new EntradaInvalidaException("Entrada inválida! Produto não deve ser nulo!");
        }
    }

}
