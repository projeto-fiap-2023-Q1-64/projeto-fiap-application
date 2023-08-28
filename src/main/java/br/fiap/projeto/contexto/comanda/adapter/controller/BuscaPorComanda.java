package br.fiap.projeto.contexto.comanda.adapter.controller;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorComandaPortControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.BuscaPorComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaPortUseCase;

public class BuscaPorComanda implements IBuscaPorComandaPortControllerAdapter {

    private final IBuscaPorComandaPortUseCase buscaPorComandaPortUseCase;

    public BuscaPorComanda(IBuscaPorComandaPortUseCase buscaPorComandaPortUseCase) {
        this.buscaPorComandaPortUseCase = buscaPorComandaPortUseCase;
    }

    @Override
    public ComandaDTO buscaPorComanda(BuscaPorComandaDTO buscaPorComandaDTO) throws ExceptionMessage {
        return ComandaDTO.newInstanceFromComanda(
                buscaPorComandaPortUseCase.buscaComandaPorStatus(buscaPorComandaDTO.getCodigoComanda()));
    }

}
