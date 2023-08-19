package br.fiap.projeto.contexto.pedido.adapter.controller.rest.response;

import br.fiap.projeto.contexto.pedido.entity.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemPedidoDTO {
    private ItemPedidoCodigo codigo;
    private Pedido pedido;
    private Integer quantidade;
    private String produtoNome;
    private String produtoDescricao;
    private Double valorUnitario;
    private CategoriaProduto categoriaProduto;
    private String imagem;
    private Integer tempoPreparoMin;
}