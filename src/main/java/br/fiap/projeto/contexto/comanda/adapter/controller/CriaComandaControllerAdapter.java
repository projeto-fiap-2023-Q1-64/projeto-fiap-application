package br.fiap.projeto.contexto.comanda.adapter.controller;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.ICriarComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.CriarComandaDTO;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaDuplicadaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.ICriarComandaUseCase;

public class CriaComandaControllerAdapter implements ICriarComandaControllerAdapter {

    private final ICriarComandaUseCase criarComandaUseCase;

    public CriaComandaControllerAdapter(ICriarComandaUseCase criarComandaUseCase) {
        this.criarComandaUseCase = criarComandaUseCase;
    }

    @Override
    public ComandaDTO criaComanda(CriarComandaDTO criarComandaDTO)
            throws EntradaInvalidaException, ComandaDuplicadaException {
        return ComandaDTO
                .newInstanceFromComanda(criarComandaUseCase.criarComanda(criarComandaDTO.getCodigoPedido()));
    }
}
