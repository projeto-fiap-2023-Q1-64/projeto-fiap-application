package br.fiap.projeto.contexto.comanda.domain.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.ItemComanda;
import br.fiap.projeto.contexto.comanda.domain.ProdutoComanda;
import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;

public class ComandaDTO {

	private UUID codigoPedido;

	private List<ItemComanda> itens;

	private StatusComanda status;

	private Date dataComanda;

	private ProdutoComanda produto;

	public ComandaDTO(UUID codigoPedido, List<ItemComanda> itens, StatusComanda status, Date dataComanda) {
		this.codigoPedido = codigoPedido;
		this.itens = itens;
		this.status = status;
		this.dataComanda = dataComanda;
	}

	public UUID getCodigoPedido() {
		return codigoPedido;
	}

	public List<ItemComanda> getItens() {
		return itens;
	}

	public StatusComanda getStatus() {
		return status;
	}

	public Date getDataComanda() {
		return dataComanda;
	}

	

}
