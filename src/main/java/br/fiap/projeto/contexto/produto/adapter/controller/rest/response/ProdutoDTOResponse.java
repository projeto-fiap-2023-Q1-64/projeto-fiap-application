package br.fiap.projeto.contexto.produto.adapter.controller.rest.response;

import br.fiap.projeto.contexto.produto.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTOResponse {

    private String codigo;

    private String nome;

    private String descricao;

    private Double preco;

    private String categoria;

    private String imagem;

    private Integer tempoPreparoMin;

    public static ProdutoDTOResponse newInstanceByProduto(Produto produto) {
        return new ProdutoDTOResponse(produto.getCodigo(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getCategoria().name(), produto.getImagem(), produto.getTempoPreparoMin());
    }
}


