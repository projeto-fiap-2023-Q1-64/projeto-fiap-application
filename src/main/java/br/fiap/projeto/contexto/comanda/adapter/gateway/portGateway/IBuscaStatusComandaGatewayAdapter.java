package br.fiap.projeto.contexto.comanda.adapter.gateway.portGateway;

import java.util.List;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

public interface IBuscaStatusComandaGatewayAdapter {
    List<Comanda> buscaComandaPorStatus(StatusComanda statusComanda) throws ExceptionMessage;
}
