package br.fiap.projeto.contexto.pagamento.usecase.port.repository;

import br.fiap.projeto.contexto.pagamento.entity.integration.PagamentoPedido;
import br.fiap.projeto.contexto.pagamento.entity.integration.PagamentoPedidoResponse;
import br.fiap.projeto.contexto.pagamento.external.integration.port.PagamentoPedidoResponseDTO;

import java.util.List;

public interface IPagamentoPedidoIntegrationGateway {
    List<PagamentoPedido> buscaPedidosAPagar();

    void atualizaStatusPagamentoPedido(PagamentoPedidoResponse pagamentoPedidoResponse);
}
