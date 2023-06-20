package br.fiap.projeto.contexto.produto.domain.dto;

import java.util.UUID;

public class ProdutoDTO {

    private UUID codigo;

    private String nome;

    private String descricao;

    private Double preco;

    private String categoria;

    private String imagem;

    private Integer tempoPreparoMin;

    public ProdutoDTO(UUID codigo, String nome, String descricao, Double preco, String categoria, String imagem, Integer tempoPreparoMin) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.imagem = imagem;
        this.tempoPreparoMin = tempoPreparoMin;
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
}


