package br.fiap.projeto.contexto.comanda.adapter.controller.port;

import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.BuscaPorComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

public interface IBuscaPorComandaControllerAdapter {
    ComandaDTO buscaPorComanda(BuscaPorComandaDTO buscaPorComandaDTO) throws ExceptionMessage;
}
