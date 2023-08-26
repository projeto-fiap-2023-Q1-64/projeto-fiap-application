package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.usecase.enums.MensagemErro;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoPagamentoIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoPagamentoIntegrationUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoWorkFlowUseCase;

import java.util.UUID;

public class PedidoPagamentoIntegrationUseCase extends AbstractPedidoUseCase implements IPedidoPagamentoIntegrationUseCase {
    final IPedidoPagamentoIntegrationAdapterGateway pedidoPagamentoIntegrationAdapterGateway;
    final IPedidoWorkFlowUseCase pedidoWorkFlowUseCase;

    public PedidoPagamentoIntegrationUseCase(IPedidoRepositoryAdapterGateway pedidoRepositoryAdapterGateway,
                                             IPedidoPagamentoIntegrationAdapterGateway pedidoPagamentoIntegrationAdapterGateway,
                                             IPedidoWorkFlowUseCase pedidoWorkFlowUseCase) {
        super(pedidoRepositoryAdapterGateway);
        this.pedidoPagamentoIntegrationAdapterGateway = pedidoPagamentoIntegrationAdapterGateway;
        this.pedidoWorkFlowUseCase = pedidoWorkFlowUseCase;
    }

    private Boolean isPagamentoAprovado(UUID codigoPedido) {
        return pedidoPagamentoIntegrationAdapterGateway.buscaStatusPagamentoPorCodigoPedido(codigoPedido).isPago();
    }

    @Override
    public Pedido pagar(UUID codigoPedido) throws Exception {
        if(!pedidoExists(codigoPedido)){
            throw new Exception(MensagemErro.PEDIDO_NOT_FOUND.getMessage());
        }

        if(!isPagamentoAprovado(codigoPedido)){
            throw new Exception(MensagemErro.PEDIDO_NOT_APPROVED.getMessage());
        }
        return pedidoWorkFlowUseCase.pagar(codigoPedido);
    }
}
