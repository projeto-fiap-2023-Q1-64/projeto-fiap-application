package br.fiap.projeto.contexto.comanda.adapter.controller;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;

import java.util.UUID;

public class FinalizaComandaControllerAdapter implements IAtualizaComandaControllerAdapter {

    private final IAtualizarComandaUseCase finalizarComandaUseCase;

    public FinalizaComandaControllerAdapter(IAtualizarComandaUseCase finalizarComandaUseCase) {
        this.finalizarComandaUseCase = finalizarComandaUseCase;
    }

    @Override
    public ComandaDTO atualizaStatusComanda(UUID codigoComanda) throws EntradaInvalidaException {
        return ComandaDTO.newInstanceFromComanda(
                finalizarComandaUseCase.alterarStatus(codigoComanda));
    }

}
