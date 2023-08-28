package br.fiap.projeto.contexto.comanda.adapter.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusComandaPortControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.BuscaPorStatusComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusPortUseCase;

public class BuscaStatusFinalizadoComandaControlleAdapter implements IBuscaPorStatusComandaPortControllerAdapter {

    private final IBuscaPorStatusPortUseCase buscarPorStatusPortUseCase;

    public BuscaStatusFinalizadoComandaControlleAdapter(
            IBuscaPorStatusPortUseCase buscarPorStatusPortUseCase) {
        this.buscarPorStatusPortUseCase = buscarPorStatusPortUseCase;
    }

    @Override
    public List<ComandaDTO> buscaPorStatus(BuscaPorStatusComandaDTO buscaStatusDTO) throws ExceptionMessage {
        return buscarPorStatusPortUseCase.buscaComandaPorStatus(buscaStatusDTO.getStatusComanda()).stream()
                .map(ComandaDTO::newInstanceFromComanda).collect(Collectors.toList());
    }

}
