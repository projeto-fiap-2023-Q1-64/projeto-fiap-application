package br.fiap.projeto.contexto.pedido.application.rest.request;

import br.fiap.projeto.contexto.pedido.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.pedido.infrastructure.integration.port.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProdutoPedidoDTO {
    private UUID codigo;
    private String nome;
    private String descricao;
    private Double preco;
    private CategoriaProduto categoria;
    private String imagem;
    private Integer tempoPreparoMin;
    public ProdutoPedidoDTO(Produto produto){
        this.codigo = UUID.fromString(produto.getCodigo());
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.categoria = CategoriaProduto.valueOf(produto.getCategoria());
        this.imagem = produto.getImagem();
        this.tempoPreparoMin = produto.getTempoPreparoMin();
    }
}