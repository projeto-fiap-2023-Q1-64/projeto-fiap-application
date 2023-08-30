package br.fiap.projeto.contexto.pagamento.adapter.controller;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IProcessaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
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
    public PagamentoDTOResponse criaNovoPagamento(PedidoAPagarDTORequest pedidoAPagarDTORequest) {
        //TODO adicionar validações para impedir que um pagamento seja criado para um pedido já em banco
        processaNovoPagamentoUseCase.criaNovoPagamento(pedidoAPagarDTORequest.conversorDePedidoAPagarDTOParaPagamento());
        return new PagamentoDTOResponse(getByCodigo(pedidoAPagarDTORequest));
    }

    private Pagamento getByCodigo(PedidoAPagarDTORequest pedidoAPagarDTORequest) {
        return buscaPagamentoUseCase.findByCodigoPedido(pedidoAPagarDTORequest.getCodigoPedido());
    }

}
