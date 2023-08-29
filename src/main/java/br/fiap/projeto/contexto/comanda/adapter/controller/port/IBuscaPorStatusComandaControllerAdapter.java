package br.fiap.projeto.contexto.comanda.adapter.controller.port;

import java.util.List;

import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.BuscaPorStatusComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

public interface IBuscaPorStatusComandaControllerAdapter {
    List<ComandaDTO> buscaPorStatus(BuscaPorStatusComandaDTO buscaStatusComandaDTO) throws ExceptionMessage, Exception;
}
