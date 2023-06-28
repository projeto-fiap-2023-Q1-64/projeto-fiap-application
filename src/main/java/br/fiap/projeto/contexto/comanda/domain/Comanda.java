package br.fiap.projeto.contexto.comanda.domain;

import java.util.Date;
import java.util.List;

import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;

public class Comanda {
 
	private Long codigoPedido;
	 
	private List<ItemComanda> itens;
	 
	private StatusComanda status;
	 
	private Date dataComanda;

	public Long getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(Long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public List<ItemComanda> getItens() {
		return itens;
	}

	public void setItens(List<ItemComanda> itens) {
		this.itens = itens;
	}

	public StatusComanda getStatus() {
		return status;
	}

	public void setStatus(StatusComanda status) {
		this.status = status;
	}

	public Date getDataComanda() {
		return dataComanda;
	}

	public void setDataComanda(Date dataComanda) {
		this.dataComanda = dataComanda;
	}
}
 
