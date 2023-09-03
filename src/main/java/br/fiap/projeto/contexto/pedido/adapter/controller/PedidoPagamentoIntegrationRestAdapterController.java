package br.fiap.projeto.contexto.pedido.adapter.controller;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoPagamentoIntegrationRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.adapter.mapper.PagamentoMapper;
import br.fiap.projeto.contexto.pedido.adapter.mapper.PedidoDtoMapper;
import br.fiap.projeto.contexto.pedido.external.integration.port.Pagamento;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoPagamentoIntegrationUseCase;

import java.util.UUID;

public class PedidoPagamentoIntegrationRestAdapterController implements IPedidoPagamentoIntegrationRestAdapterController {
    final IPedidoPagamentoIntegrationUseCase pedidoPagamentoIntegrationUseCase;

    public PedidoPagamentoIntegrationRestAdapterController(IPedidoPagamentoIntegrationUseCase pedidoPagamentoIntegrationUseCase) {
        this.pedidoPagamentoIntegrationUseCase = pedidoPagamentoIntegrationUseCase;
    }
    @Override
    public PedidoDTO atualizarPagamentoPedido(UUID codigoPedido) throws Exception {
        return PedidoDtoMapper.toDto(pedidoPagamentoIntegrationUseCase.atualizarPagamentoPedido(codigoPedido));
    }

    @Override
    public void recebeRetornoPagamento(Pagamento retornoPagamento) throws Exception {
        this.pedidoPagamentoIntegrationUseCase.recebeRetornoPagamento(PagamentoMapper.toPagamentoPedido(retornoPagamento));
    }
}
