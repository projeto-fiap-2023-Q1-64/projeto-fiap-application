package br.fiap.projeto.contexto.comanda.adapter.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.BuscaPorStatusComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusComandaUseCase;

public class BuscaStatusPreparacaoComandaControllerAdapter implements IBuscaPorStatusComandaControllerAdapter {

    private final IBuscaPorStatusComandaUseCase buscarPorStatusComandaUseCase;

    public BuscaStatusPreparacaoComandaControllerAdapter(
            IBuscaPorStatusComandaUseCase buscarPorStatusComandaUseCase) {
        this.buscarPorStatusComandaUseCase = buscarPorStatusComandaUseCase;
    }

    @Override
    public List<ComandaDTO> buscaPorStatus(BuscaPorStatusComandaDTO buscaStatusDTO) throws Exception {
        return buscarPorStatusComandaUseCase.buscaComandaPorStatus(buscaStatusDTO.getStatusComanda()).stream()
                .map(ComandaDTO::newInstanceFromComanda).collect(Collectors.toList());
    }

}
