package br.fiap.projeto.contexto.pagamento.adapter.mapper;

import br.fiap.projeto.contexto.pagamento.entity.integration.PagamentoPedido;
import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;

public class PagamentoPedidoMapper {
    public static PagamentoPedido toPagamentoPedido(Pedido pedido) {
        return new PagamentoPedido(pedido.getCodigo(),pedido.getValorTotal());
    }
}