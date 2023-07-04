package br.fiap.projeto.contexto.comanda.domain.dto;

import java.util.UUID;

import br.fiap.projeto.contexto.produto.domain.Produto;

public class ItemComandaDTO {
    private UUID codigoProduto;
    private UUID codigoPedido;
    private Produto produto;
    private int qtde;

    public ItemComandaDTO(UUID codigoProduto, UUID codigoPedido, Produto produto, int qtde) {
        this.codigoProduto = codigoProduto;
        this.codigoPedido = codigoPedido;
        this.produto = produto;
        this.qtde = qtde;
    }

    public UUID getCodigoProduto() {
        return codigoProduto;
    }

    public UUID getCodigoPedido() {
        return codigoPedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQtde() {
        return qtde;
    }

}
