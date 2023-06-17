package br.fiap.projeto.contexto.comanda.domain;

import br.fiap.projeto.contexto.comanda.domain.vo.ProdutoComanda;

public class ItemComanda {
 
	private ProdutoComanda produto;
	 
	private Integer quantidade;

	public ProdutoComanda getProduto() {
		return produto;
	}

	public void setProduto(ProdutoComanda produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
 
