package br.fiap.projeto.contexto.comanda.domain;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.dto.ProdutoComandaDTO;

public class ProdutoComanda {
    
    private UUID codigo;

    private String nome;

    private String descricao;

    public ProdutoComanda(UUID codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    public ProdutoComanda(ProdutoComandaDTO produtoComandaDTO) {
        this.codigo = produtoComandaDTO.getCodigo();
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

    public ProdutoComandaDTO toProdutoComandaDTO(){
        return new ProdutoComandaDTO(codigo, nome, descricao);
    }

}
