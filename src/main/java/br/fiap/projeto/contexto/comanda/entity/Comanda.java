package br.fiap.projeto.contexto.comanda.entity;

import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.StatusNuloException;

import java.util.UUID;

public class Comanda {

	private UUID codigoComanda;

	private UUID codigoPedido;

	private StatusComanda status;

	public Comanda() {
	}

	public Comanda(UUID codigoComanda, UUID codigoPedido, StatusComanda status)
			throws EntradaInvalidaException, StatusNuloException {
		this.codigoComanda = codigoComanda;
		this.codigoPedido = codigoPedido;
		this.status = status;
		validarDados();
	}

	public Comanda(ComandaDTO comandaDTO) throws EntradaInvalidaException {
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

	public void validarDados() throws EntradaInvalidaException, StatusNuloException {
		if ((codigoComanda == null) || (codigoPedido == null)) {
			throw new EntradaInvalidaException("Comanda com parametros vazios");
		}
		if (status.equals(null)) {
			throw new EntradaInvalidaException("Comanda com parametros vazios");
		}
	}

}
