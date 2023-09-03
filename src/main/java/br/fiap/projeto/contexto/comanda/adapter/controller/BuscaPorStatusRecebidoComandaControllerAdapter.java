package br.fiap.projeto.contexto.comanda.adapter.controller;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusRecebidoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusRecebidoComandaUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaPorStatusRecebidoComandaControllerAdapter implements IBuscaPorStatusRecebidoComandaControllerAdapter {

    private final IBuscaPorStatusRecebidoComandaUseCase buscaPorStatusRecebidoComandaUseCase;

    public BuscaPorStatusRecebidoComandaControllerAdapter(
            IBuscaPorStatusRecebidoComandaUseCase buscaPorStatusRecebidoComandaUseCase) {
        this.buscaPorStatusRecebidoComandaUseCase = buscaPorStatusRecebidoComandaUseCase;
    }

    @Override
    public List<ComandaDTO> buscaPorStatusRecebido() throws Exception {
        return buscaPorStatusRecebidoComandaUseCase.buscaComandaPorStatusRecebido().stream()
                .map(ComandaDTO::newInstanceFromComanda).collect(Collectors.toList());
    }

}
