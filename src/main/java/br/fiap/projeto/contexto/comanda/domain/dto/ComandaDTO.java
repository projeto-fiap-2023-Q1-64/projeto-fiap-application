package br.fiap.projeto.contexto.comanda.domain.dto;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;

public class ComandaDTO {

	private UUID codigoComanda;

	private UUID codigoPedido;

	private StatusComanda status;

	public ComandaDTO(UUID codigoComanda, UUID codigoPedido, StatusComanda status) {
		this.codigoComanda = codigoComanda;
		this.codigoPedido = codigoPedido;
		this.status = status;
	}

	public UUID getCodigoPedido() {
		return codigoPedido;
	}

	public StatusComanda getStatus() {
		return status;
	}

	public UUID getCodigoComanda() {
		return codigoComanda;
	}

}
