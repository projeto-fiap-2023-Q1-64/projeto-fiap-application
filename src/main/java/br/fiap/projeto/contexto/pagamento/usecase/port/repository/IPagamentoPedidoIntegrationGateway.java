package br.fiap.projeto.contexto.pagamento.usecase.port.repository;

import br.fiap.projeto.contexto.pagamento.entity.integration.PagamentoPedido;

import java.util.List;

public interface IPagamentoPedidoIntegrationGateway {
    List<PagamentoPedido> buscaPedidosAPagar();
}
