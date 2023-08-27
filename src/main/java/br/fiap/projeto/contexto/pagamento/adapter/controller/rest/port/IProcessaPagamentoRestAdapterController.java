package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;


public interface IProcessaPagamentoRestAdapterController {

     PagamentoDTOResponse criaNovoPagamento(PedidoAPagarDTORequest pedidoAPagarDTORequest);

}
