package br.fiap.projeto.contexto.comanda.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;

public class Comanda {

	private UUID codigoComanda;

	private UUID codigoPedido;

	private List<ItemComanda> itens;

	private StatusComanda status;

	private Date dataComanda;

	public Comanda(UUID codigoPedido, List<ItemComanda> itens, StatusComanda status,
			Date dataComanda) {
		this.codigoPedido = codigoPedido;
		this.itens = itens;
		this.dataComanda = dataComanda;
		this.status = status;
	}

	public Comanda(ComandaDTO comandaDTO) {

		this.codigoPedido = comandaDTO.getCodigoPedido();
		this.itens = comandaDTO.getItens();
		this.dataComanda = comandaDTO.getDataComanda();
		this.status = comandaDTO.getStatus();

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

	public ComandaDTO toComandaDTO() {
		return new ComandaDTO(codigoPedido, itens, status, dataComanda);
	}

}
