package br.fiap.projeto.contexto.comanda.adapter.controller;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.BuscaPorComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaUseCase;

public class BuscaPorComandaControllerAdapter implements IBuscaPorComandaControllerAdapter {

    private final IBuscaPorComandaUseCase buscaPorComandaUseCase;

    public BuscaPorComandaControllerAdapter(IBuscaPorComandaUseCase buscaPorComandaUseCase) {
        this.buscaPorComandaUseCase = buscaPorComandaUseCase;
    }

    @Override
    public ComandaDTO buscaPorComanda(BuscaPorComandaDTO buscaPorComandaDTO) throws ExceptionMessage {
        return ComandaDTO.newInstanceFromComanda(
                buscaPorComandaUseCase.buscaComandaPorStatus(buscaPorComandaDTO.getCodigoComanda()));
    }

}
