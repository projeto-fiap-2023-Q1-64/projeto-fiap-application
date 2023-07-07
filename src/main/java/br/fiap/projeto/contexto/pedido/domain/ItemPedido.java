package br.fiap.projeto.contexto.pedido.domain;

import br.fiap.projeto.contexto.pedido.application.rest.response.ItemPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.enums.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemPedido {
	private ItemPedidoCodigo codigo;
	private Pedido pedido;
	private Integer quantidade;
	private String produtoNome;
	private String produtoDescricao;
	private Double valorUnitario;
	private CategoriaProduto categoriaProduto;
	private String imagem;
	private Integer tempoPreparoMin;

	public void adicionarQuantidade() {
		this.quantidade++;
	}
	public void reduzirQuantidade() {
		this.quantidade--;
	}
	public ItemPedidoDTO toItemPedidoDTO() {
		return new ItemPedidoDTO(this.codigo, this.pedido, this.quantidade, this.produtoNome, this.produtoDescricao, this.valorUnitario, this.categoriaProduto, this.imagem, this.tempoPreparoMin);
	}
}