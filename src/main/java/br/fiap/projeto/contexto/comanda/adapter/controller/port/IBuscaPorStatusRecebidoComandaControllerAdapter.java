package br.fiap.projeto.contexto.comanda.adapter.controller.port;

import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

import java.util.List;

public interface IBuscaPorStatusRecebidoComandaControllerAdapter {
    List<ComandaDTO> buscaPorStatusRecebido() throws EntradaInvalidaException, Exception;
}
