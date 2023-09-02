package br.fiap.projeto.contexto.pagamento.adapter.controller;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IAtualizaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;

public class AtualizaStatusPagamentoRestAdapterController implements IAtualizaPagamentoRestAdapterController {

    private final IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase;

    public AtualizaStatusPagamentoRestAdapterController(IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase) {
        this.atualizaStatusPagamentoUsecase = atualizaStatusPagamentoUsecase;
    }

    /**
     * Atualização de Status de Pagamento:<br>
     * <li>Pagamento PENDENTE poderá ser colocado EM PROCESSAMENTO</li>
     * <li>Pagamento EM PROCESSAMENTO poderá ser colocado em: APROVADO, REJEITADO</li>
     * <li>Pagamento REJEITADO poderá ser colocado em: CANCELADO</li>
     * @param pagamentoDTORequest
     */
    @Override
    public void atualizaStatusPagamento(PagamentoDTORequest pagamentoDTORequest) {
        atualizaStatusPagamentoUsecase.atualizaStatusPagamento(pagamentoDTORequest.getCodigoPedido(), pagamentoDTORequest.getStatus());
    }
}
