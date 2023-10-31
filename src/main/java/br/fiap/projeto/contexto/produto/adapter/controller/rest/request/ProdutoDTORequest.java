package br.fiap.projeto.contexto.produto.adapter.controller.rest.request;

import br.fiap.projeto.contexto.produto.entity.Produto;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.usecase.exception.EntradaInvalidaException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProdutoDTORequest {

    private String nome;

    private String descricao;

    private Double preco;

    private String categoria;

    private String imagem;

    private Integer tempoPreparoMin;

    public Produto toProduto() throws EntradaInvalidaException {
        return new Produto(nome, descricao, preco, CategoriaProduto.valueOf(categoria), imagem, tempoPreparoMin);
    }
}
