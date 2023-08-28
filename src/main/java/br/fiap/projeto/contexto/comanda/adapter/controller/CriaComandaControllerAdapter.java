package br.fiap.projeto.contexto.comanda.adapter.controller;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.ICriarComandaPortControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.CriarComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.ICriarComandaPortUseCase;

public class CriaComandaControllerAdapter implements ICriarComandaPortControllerAdapter {

    private final ICriarComandaPortUseCase criarComandaPortUseCase;

    public CriaComandaControllerAdapter(ICriarComandaPortUseCase criarComandaPortUseCase) {
        this.criarComandaPortUseCase = criarComandaPortUseCase;
    }

    @Override
    public ComandaDTO criaComanda(CriarComandaDTO criarComandaDTO) throws ExceptionMessage {
        return ComandaDTO
                .newInstanceFromComanda(criarComandaPortUseCase.criarComanda(criarComandaDTO.getCodigoPedido()));
    }
}
