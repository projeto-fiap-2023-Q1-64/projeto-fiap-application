package br.fiap.projeto.contexto.pagamento.adapter.controller;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IEnviaPagamentoGatewayRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoAEnviarAoGatewayDTORequest;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IEnviaPagamentoAoGatewayPagamentosUseCase;

public class EnviaPagamentoAoGatewayRestAdapterController implements  IEnviaPagamentoGatewayRestAdapterController {

    private final IEnviaPagamentoAoGatewayPagamentosUseCase enviaPagamentoAoGatewayPagamentosUseCase;

    public EnviaPagamentoAoGatewayRestAdapterController(IEnviaPagamentoAoGatewayPagamentosUseCase enviaPagamentoAoGatewayPagamentosUseCase) {
        this.enviaPagamentoAoGatewayPagamentosUseCase = enviaPagamentoAoGatewayPagamentosUseCase;
    }

    @Override
    public PagamentoAEnviarAoGatewayDTORequest preparaParaEnviarPagamentoAoGateway(PagamentoAEnviarAoGatewayDTORequest pagamentoDTORequest) {

        enviaPagamentoAoGatewayPagamentosUseCase
                .verificaPagamentoAntesDeEnviarAoGateway(
                        pagamentoDTORequest.getCodigoPedido(), pagamentoDTORequest.getStatusPagamento()
                );

        enviaPagamentoAoGatewayPagamentosUseCase
                .validaStatusAtualDoPagamentoAntesDeEnviarAoGateway(
                        pagamentoDTORequest.getCodigoPedido(), pagamentoDTORequest.getStatusPagamento()
                );

        return new PagamentoAEnviarAoGatewayDTORequest(
                enviaPagamentoAoGatewayPagamentosUseCase
                        .atualizaStatusNovoAoEnviarPagamentoAoGateway(
                                pagamentoDTORequest.getCodigoPedido(), pagamentoDTORequest.getStatusPagamento()
                        )
        );
    }

    @Override
    public void enviaParaGatewayDePagamento(PagamentoAEnviarAoGatewayDTORequest pagamentoDTORequest) {
        System.out.println("RestAdapterController: Enviando request de pagamento ao sistema externo...");
        enviaPagamentoAoGatewayPagamentosUseCase
                .enviaRequestAoSistemaExternoPagamentos(
                        pagamentoDTORequest.getCodigoPedido(), pagamentoDTORequest.getStatusPagamento()
                );
    }

}
