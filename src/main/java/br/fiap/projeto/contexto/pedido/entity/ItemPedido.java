package br.fiap.projeto.contexto.pedido.entity;

import java.util.Objects;
import java.util.UUID;

import br.fiap.projeto.contexto.pedido.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;

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

	public ItemPedido(UUID pedidoCodigo, UUID produtoCodigo, Pedido pedido, Integer quantidade, String produtoNome,
			String produtoDescricao, Double valorUnitario, CategoriaProduto categoriaProduto, String imagem,
			Integer tempoPreparoMin) throws NoItensException, InvalidStatusException {
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
		validarItemPedido();
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ItemPedido that = (ItemPedido) o;
		return Objects.equals(pedidoCodigo, that.pedidoCodigo) && Objects.equals(produtoCodigo, that.produtoCodigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pedidoCodigo, produtoCodigo);
	}

	private void validarItemPedido() throws NoItensException, InvalidStatusException {
		if ((pedidoCodigo == null) || (produtoCodigo == null) || (quantidade == null) || (quantidade <= 0)
				|| (produtoNome == null)
				|| (produtoDescricao == null) || (valorUnitario == null) || (valorUnitario <= 0)
				|| (tempoPreparoMin == null) || (tempoPreparoMin <= 0)) {
			throw new NoItensException("erro");
		}

		if (categoriaProduto.equals(null)) {
			throw new NullPointerException("Status Nulo");
		}
	}
}