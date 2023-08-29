package br.fiap.projeto.contexto.comanda.adapter.controller;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;

public class FinalizaComandaControllerAdapter implements IAtualizaComandaControllerAdapter {

    private final IAtualizarComandaUseCase atualizarComandaUseCase;

    public FinalizaComandaControllerAdapter(IAtualizarComandaUseCase atualizarComandaUseCase) {
        this.atualizarComandaUseCase = atualizarComandaUseCase;
    }

    @Override
    public ComandaDTO atualizaStatusComanda(UUID codigoComanda) throws ExceptionMessage {
        return ComandaDTO.newInstanceFromComanda(
                atualizarComandaUseCase.atualizar(codigoComanda));
    }

}
