package br.fiap.projeto.contexto.pedido.adapter.mapper;

import br.fiap.projeto.contexto.pedido.entity.integration.PagamentoPedido;
import br.fiap.projeto.contexto.pedido.external.integration.port.Pagamento;

public class PagamentoMapper {
    public static PagamentoPedido toPagamentoPedido(Pagamento pagamento){
        return new PagamentoPedido(pagamento.getCodigoPedido(),
                pagamento.getDataPagamento(),
                pagamento.getStatus());
    }
}
