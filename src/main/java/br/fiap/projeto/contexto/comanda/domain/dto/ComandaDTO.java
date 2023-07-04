package br.fiap.projeto.contexto.comanda.domain.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;

public class ComandaDTO {

	@JsonProperty("codigo")
	private UUID codigoPedido;

	private List<ItemComandaDTO> itens;

	private StatusComanda status;

	private Date dataComanda;

	public ComandaDTO(UUID codigoPedido, List<ItemComandaDTO> itens, StatusComanda status,
			Date dataComanda) {
		this.codigoPedido = codigoPedido;
		this.itens = itens;
		this.status = status;
		this.dataComanda = dataComanda;
	}

	public UUID getCodigoPedido() {
		return codigoPedido;
	}

	public List<ItemComandaDTO> getItens() {
		return itens;
	}

	public StatusComanda getStatus() {
		return status;
	}

	public Date getDataComanda() {
		return dataComanda;
	}

}
