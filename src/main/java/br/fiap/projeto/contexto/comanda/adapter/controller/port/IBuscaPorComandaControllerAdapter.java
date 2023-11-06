package br.fiap.projeto.contexto.comanda.adapter.controller.port;

import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.BuscaPorComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.StatusNuloException;

public interface IBuscaPorComandaControllerAdapter {
    ComandaDTO buscaPorComanda(BuscaPorComandaDTO buscaPorComandaDTO)
            throws EntradaInvalidaException, ComandaNaoEncontradaException, StatusNuloException;
}
