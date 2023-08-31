package br.fiap.projeto.contexto.pagamento.adapter.controller;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IProcessaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoNovoDTOResponse;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IProcessaNovoPagamentoUseCase;

public class ProcessaNovoPagamentoRestAdapterController implements IProcessaPagamentoRestAdapterController {

    private final IProcessaNovoPagamentoUseCase processaNovoPagamentoUseCase;

    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;
    public ProcessaNovoPagamentoRestAdapterController(IProcessaNovoPagamentoUseCase processaNovoPagamentoUseCase, IBuscaPagamentoUseCase buscaPagamentoUseCase) {
        this.processaNovoPagamentoUseCase = processaNovoPagamentoUseCase;
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
    }

    @Override
    public PagamentoNovoDTOResponse criaNovoPagamento(PedidoAPagarDTORequest pedidoAPagarDTORequest) {
        return new PagamentoNovoDTOResponse(processaNovoPagamentoUseCase
                                    .criaNovoPagamento(
                                            pedidoAPagarDTORequest.conversorDePedidoAPagarDTOParaPagamento()));
    }

}
