package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.entity.integration.PagamentoPedido;
import br.fiap.projeto.contexto.pedido.usecase.enums.MensagemErro;
import br.fiap.projeto.contexto.pedido.usecase.exception.IntegrationPagamentoException;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoPagamentoIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoPagamentoIntegrationUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoQueryUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoWorkFlowUseCase;

import java.util.UUID;

public class PedidoPagamentoIntegrationUseCase extends AbstractPedidoUseCase implements IPedidoPagamentoIntegrationUseCase {
    private final IPedidoPagamentoIntegrationAdapterGateway pedidoPagamentoIntegrationAdapterGateway;
    private final IPedidoWorkFlowUseCase pedidoWorkFlowUseCase;

    public PedidoPagamentoIntegrationUseCase(IPedidoRepositoryAdapterGateway pedidoRepositoryAdapterGateway,
                                             IPedidoPagamentoIntegrationAdapterGateway pedidoPagamentoIntegrationAdapterGateway,
                                             IPedidoWorkFlowUseCase pedidoWorkFlowUseCase) {
        super(pedidoRepositoryAdapterGateway);
        this.pedidoPagamentoIntegrationAdapterGateway = pedidoPagamentoIntegrationAdapterGateway;
        this.pedidoWorkFlowUseCase = pedidoWorkFlowUseCase;
    }

    @Override
    public Pedido atualizarPagamentoPedido(UUID codigoPedido) throws Exception {
        if(!pedidoExists(codigoPedido)){
            throw new Exception(MensagemErro.PEDIDO_NOT_FOUND.getMessage());
        }
        Pedido pedido = this.buscar(codigoPedido);
        if(!pedido.getStatus().equals(StatusPedido.RECEBIDO)){
            throw new InvalidStatusException(MensagemErro.INVALID_STATUS.getMessage());
        }
        PagamentoPedido pagamentoPedido;
        try {
            pagamentoPedido = pedidoPagamentoIntegrationAdapterGateway.buscaStatusPagamentoPorCodigoPedido(codigoPedido);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IntegrationPagamentoException(MensagemErro.PAGAMENTO_INTEGRATION_ERROR.getMessage() + " " + e.getMessage());
        }
        if(pagamentoPedido.isPago()){
            return pedidoWorkFlowUseCase.pagar(codigoPedido);
        }
        if(pagamentoPedido.isCanceled()){
            return pedidoWorkFlowUseCase.cancelar(codigoPedido);
        }

        throw new Exception(MensagemErro.PEDIDO_NOT_APPROVED.getMessage());
    }
}
