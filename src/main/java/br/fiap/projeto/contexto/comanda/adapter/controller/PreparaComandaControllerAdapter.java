package br.fiap.projeto.contexto.comanda.adapter.controller;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;

import java.util.UUID;

public class PreparaComandaControllerAdapter implements IAtualizaComandaControllerAdapter {

    private final IAtualizarComandaUseCase prepararComandaUseCase;

    public PreparaComandaControllerAdapter(IAtualizarComandaUseCase prepararComandaUseCase) {
        this.prepararComandaUseCase = prepararComandaUseCase;
    }

    @Override
    public ComandaDTO atualizaStatusComanda(UUID codigoComanda) throws ExceptionMessage {
        return ComandaDTO.newInstanceFromComanda(prepararComandaUseCase.atualizar(codigoComanda));
    }

}
