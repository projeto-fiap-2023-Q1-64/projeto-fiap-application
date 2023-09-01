package br.fiap.projeto.contexto.comanda.adapter.controller;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;

public class PreparaComandaControllerAdapter implements IAtualizaComandaControllerAdapter {

    private final IAtualizarComandaUseCase prepararComandaUseCase;

    public PreparaComandaControllerAdapter(IAtualizarComandaUseCase prepararComandaUseCase) {
        this.prepararComandaUseCase = prepararComandaUseCase;
    }

    @Override
    public ComandaDTO atualizaStatusComanda(UUID codigoComanda) throws EntradaInvalidaException {
        return ComandaDTO.newInstanceFromComanda(prepararComandaUseCase.alterarStatus(codigoComanda));
    }

}
