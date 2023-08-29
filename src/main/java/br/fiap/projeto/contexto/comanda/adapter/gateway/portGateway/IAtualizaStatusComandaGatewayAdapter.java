package br.fiap.projeto.contexto.comanda.adapter.gateway.portGateway;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

public interface IAtualizaStatusComandaGatewayAdapter {
    Comanda atualizar(UUID codigoComanda) throws ExceptionMessage;
}
