package br.fiap.projeto.contexto.produto.application.rest.request;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
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

    public Produto toProduto() {
        return new Produto(nome, descricao, preco, CategoriaProduto.valueOf(categoria), imagem, tempoPreparoMin);
    }
}


