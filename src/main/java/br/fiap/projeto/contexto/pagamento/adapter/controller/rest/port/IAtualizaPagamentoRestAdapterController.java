package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;

public interface IAtualizaPagamentoRestAdapterController {

    void atualizaStatusPagamento(PagamentoDTORequest pagamentoDTORequest);

}
