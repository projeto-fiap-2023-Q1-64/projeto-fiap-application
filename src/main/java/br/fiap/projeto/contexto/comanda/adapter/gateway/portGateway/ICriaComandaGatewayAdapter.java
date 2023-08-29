package br.fiap.projeto.contexto.comanda.adapter.gateway.portGateway;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

public interface ICriaComandaGatewayAdapter {
    Comanda criar(Comanda criar) throws ExceptionMessage;
}