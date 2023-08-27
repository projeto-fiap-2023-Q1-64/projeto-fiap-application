package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoStatusDTOResponse;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPagamento;

//TODO verificar essa Interface - Será utilizada para operar o retorno do gateway de pgto
public interface IAtualizaPagamentoRestAdapterController {

    PagamentoStatusDTOResponse atualizaStatusPagamento(PagamentoDTORequest pagamentoDTORequest);

    void analisaStatusDoPagamento(StatusPagamento statusAtual, StatusPagamento statusRequest, PagamentoDTORequest pagamentoEmAndamentoDTORequest);
}
