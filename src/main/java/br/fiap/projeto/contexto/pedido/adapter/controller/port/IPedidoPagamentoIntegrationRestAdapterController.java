package br.fiap.projeto.contexto.pedido.adapter.controller.port;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.external.integration.port.Pagamento;

import java.util.UUID;

public interface IPedidoPagamentoIntegrationRestAdapterController {
    PedidoDTO atualizarPagamentoPedido(UUID codigoPedido) throws Exception;

    void recebeRetornoPagamento(Pagamento retornoPagamento) throws Exception;
}
