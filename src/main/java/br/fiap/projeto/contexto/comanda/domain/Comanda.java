package br.fiap.projeto.contexto.comanda.domain;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.dto.CriarComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;

public class Comanda {

	private UUID codigoComanda;

	private UUID codigoPedido;

	private StatusComanda status;

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

	public Comanda(CriarComandaDTO criarComandaDTO) {
		this.codigoPedido = criarComandaDTO.getCodigoPedido();
		this.status = StatusComanda.RECEBIDO;
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

	public ComandaDTO toComandaDTO() {
		return new ComandaDTO(codigoComanda, codigoPedido, status);
	}

	public void atualizaStatus(StatusComanda statusComanda) {
		this.status = statusComanda;
	}

}
