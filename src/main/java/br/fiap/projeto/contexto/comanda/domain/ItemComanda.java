package br.fiap.projeto.contexto.comanda.domain;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.dto.ItemComandaDTO;
import br.fiap.projeto.contexto.produto.domain.Produto;

public class ItemComanda {

	private UUID codigoProduto;
	private UUID codigoPedido;
	private Produto produto;
	private int qtde;

	public ItemComanda(UUID codigoProduto, UUID codigoPedido, Produto produto, int qtde) {
		this.codigoProduto = codigoProduto;
		this.codigoPedido = codigoPedido;
		this.produto = produto;
		this.qtde = qtde;
	}

	public ItemComanda(ItemComandaDTO itemComandaDTO) {
		this.codigoProduto = itemComandaDTO.getCodigoProduto();
		this.codigoPedido = itemComandaDTO.getCodigoPedido();
		this.produto = itemComandaDTO.getProduto();
		this.qtde = itemComandaDTO.getQtde();
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

	public ItemComandaDTO toItemComandaDTO() {
		return new ItemComandaDTO(codigoProduto, codigoPedido, produto, qtde);
	}

}
