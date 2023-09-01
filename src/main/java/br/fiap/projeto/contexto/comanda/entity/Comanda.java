package br.fiap.projeto.contexto.comanda.entity;

import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

import java.util.UUID;

public class Comanda {

	private UUID codigoComanda;

	private UUID codigoPedido;

	private StatusComanda status;

	public Comanda() {
	}

	public Comanda(UUID codigoComanda, UUID codigoPedido, StatusComanda status) {
		this.codigoComanda = codigoComanda;
		this.codigoPedido = codigoPedido;
		this.status = status;
	}

	public Comanda(ComandaDTO comandaDTO) {
		this.codigoComanda = comandaDTO.getCodigoComanda();
		this.codigoPedido = comandaDTO.getCodigoPedido();
		this.status = comandaDTO.getStatus();

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

	public void atualizaStatus(StatusComanda statusComanda) {
		this.status = statusComanda;
	}

	public void validarDados() throws EntradaInvalidaException {
		if ((codigoComanda == null) || (codigoPedido == null)) {
			throw new EntradaInvalidaException("Comanda com parametros vazios");
		}
	}

}
