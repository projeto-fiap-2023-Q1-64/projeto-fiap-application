package br.fiap.projeto.contexto.pedido.entity;

import br.fiap.projeto.contexto.pedido.entity.enums.CategoriaProduto;

import java.util.UUID;

public class ItemPedido {
	private UUID pedidoCodigo;
	private UUID produtoCodigo;
	private Pedido pedido;
	private Integer quantidade;
	private String produtoNome;
	private String produtoDescricao;
	private Double valorUnitario;
	private CategoriaProduto categoriaProduto;
	private String imagem;
	private Integer tempoPreparoMin;

	public ItemPedido() {
	}

	public ItemPedido(UUID pedidoCodigo, UUID produtoCodigo, Pedido pedido, Integer quantidade, String produtoNome, String produtoDescricao, Double valorUnitario, CategoriaProduto categoriaProduto, String imagem, Integer tempoPreparoMin) {
		this.pedidoCodigo = pedidoCodigo;
		this.produtoCodigo = produtoCodigo;
		this.pedido = pedido;
		this.quantidade = quantidade;
		this.produtoNome = produtoNome;
		this.produtoDescricao = produtoDescricao;
		this.valorUnitario = valorUnitario;
		this.categoriaProduto = categoriaProduto;
		this.imagem = imagem;
		this.tempoPreparoMin = tempoPreparoMin;
	}

	public UUID getPedidoCodigo() {
		return pedidoCodigo;
	}

	public UUID getProdutoCodigo() {
		return produtoCodigo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getProdutoNome() {
		return produtoNome;
	}

	public String getProdutoDescricao() {
		return produtoDescricao;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public String getImagem() {
		return imagem;
	}

	public Integer getTempoPreparoMin() {
		return tempoPreparoMin;
	}

	public void adicionarQuantidade() {
		this.quantidade++;
	}
	public void reduzirQuantidade() {
		this.quantidade--;
	}
}