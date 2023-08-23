package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;


public interface IProcessaPagamentoRestAdapterController {

     PagamentoDTOResponse criaNovoPagamento(PagamentoDTORequest pagamentoDTORequest);

}
