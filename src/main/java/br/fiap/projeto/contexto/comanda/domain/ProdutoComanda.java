package br.fiap.projeto.contexto.comanda.domain;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.dto.ProdutoComandaDTO;

public class ProdutoComanda {

    private UUID codigo;

    private UUID codigoProduto;

    private String nome;

    private String descricao;

    public ProdutoComanda(UUID codigo, UUID codigoProduto, String nome, String descricao) {
        this.codigo = codigo;
        this.codigoProduto = codigoProduto;
        this.nome = nome;
        this.descricao = descricao;
    }

    public ProdutoComanda(ProdutoComandaDTO produtoComandaDTO) {
        this.codigo = produtoComandaDTO.getCodigo();
        this.codigoProduto = produtoComandaDTO.getCodigoProduto();
        this.nome = produtoComandaDTO.getNome();
        this.descricao = produtoComandaDTO.getDescricao();
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

    public ProdutoComandaDTO toProdutoComandaDTO() {
        return new ProdutoComandaDTO(codigo, codigoProduto, nome, descricao);
    }

}
