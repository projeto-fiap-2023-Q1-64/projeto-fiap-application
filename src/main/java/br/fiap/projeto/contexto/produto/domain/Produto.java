package br.fiap.projeto.contexto.produto.domain;

import br.fiap.projeto.contexto.produto.application.rest.dto.ProdutoDTO;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;

import java.util.UUID;

public class Produto {

    private String codigo;

    private String nome;

    private String descricao;

    private Double preco;

    private CategoriaProduto categoria;

    private String imagem;

    private Integer tempoPreparoMin;

    public Produto(String codigo, String nome, String descricao, Double preco, CategoriaProduto categoria, String imagem, Integer tempoPreparoMin) {
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

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public Integer getTempoPreparoMin() {
        return tempoPreparoMin;
    }

    public ProdutoDTO toProdutoDTO() {
        return new ProdutoDTO(codigo.toString(), nome, descricao, preco, categoria.name(), imagem, tempoPreparoMin);
    }
}
 
