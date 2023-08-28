package br.fiap.projeto.contexto.comanda.adapter.controller;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaPortControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaPortUseCase;

public class FinalizaStatusComandaControllerAdapter implements IAtualizaComandaPortControllerAdapter {

    private final IAtualizarComandaPortUseCase atualizarComandaPortUseCase;

    public FinalizaStatusComandaControllerAdapter(IAtualizarComandaPortUseCase atualizarComandaPortUseCase) {
        this.atualizarComandaPortUseCase = atualizarComandaPortUseCase;
    }

    @Override
    public ComandaDTO atualizaStatusComanda(UUID codigoComanda) throws ExceptionMessage {
        return ComandaDTO.newInstanceFromComanda(
                atualizarComandaPortUseCase.atualizar(codigoComanda));
    }

}
