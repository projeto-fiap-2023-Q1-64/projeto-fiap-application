package br.fiap.projeto.contexto.comanda.domain.dto;

import java.util.UUID;

public class ProdutoComandaDTO {

    private UUID codigo;

    private UUID codigoProduto;

    private String nome;

    private String descricao;

    public ProdutoComandaDTO(UUID codigo, UUID codigoProduto, String nome, String descricao) {
        this.codigo = codigo;
        this.codigoProduto = codigoProduto;
        this.nome = nome;
        this.descricao = descricao;
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

    public UUID getCodigoProduto() {
        return codigoProduto;
    }

}
