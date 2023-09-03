package br.fiap.projeto.contexto.pagamento.usecase.port.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;

import java.util.UUID;

public interface IPagamentoPedidoIntegrationUseCase {

    void atualizarPagamentoPedido(Pagamento codigoPedido);
}
