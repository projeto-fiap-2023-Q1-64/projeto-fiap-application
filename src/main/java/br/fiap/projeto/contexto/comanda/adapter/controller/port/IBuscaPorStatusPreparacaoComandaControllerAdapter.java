package br.fiap.projeto.contexto.comanda.adapter.controller.port;

import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.BuscaPorStatusComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

import java.util.List;

public interface IBuscaPorStatusPreparacaoComandaControllerAdapter {
    List<ComandaDTO> buscaPorStatusPreparacao() throws ExceptionMessage, Exception;
}
