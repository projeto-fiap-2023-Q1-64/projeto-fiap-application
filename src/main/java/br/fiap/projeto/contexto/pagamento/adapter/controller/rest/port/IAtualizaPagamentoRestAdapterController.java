package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;

//TODO verificar essa Interface - Será utilizada para operar o retorno do gateway de pgto
public interface IAtualizaPagamentoRestAdapterController {

    void atualizaStatusPagamento(PagamentoDTORequest pagamentoDTORequest);

}
