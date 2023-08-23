package br.fiap.projeto.contexto.pagamento.adapter.controller;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IEnviaPagamentoGatewayRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoAEnviarAoGatewayDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IEnviaPagamentoAoGatewayPagamentosUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IProcessaNovoPagamentoUseCase;

public class EnviaPagamentoAoGatewayRestAdapterController implements  IEnviaPagamentoGatewayRestAdapterController {

    private final IEnviaPagamentoAoGatewayPagamentosUseCase enviaPagamentoAoGatewayPagamentosUseCase;

    private final IProcessaNovoPagamentoUseCase processaNovoPagamentoUseCase;
    private final IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase;

    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;

    public EnviaPagamentoAoGatewayRestAdapterController(IEnviaPagamentoAoGatewayPagamentosUseCase enviaPagamentoAoGatewayPagamentosUseCase, IProcessaNovoPagamentoUseCase processaNovoPagamentoUseCase , IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase, IBuscaPagamentoUseCase buscaPagamentoUseCase) {
        this.enviaPagamentoAoGatewayPagamentosUseCase = enviaPagamentoAoGatewayPagamentosUseCase;
        this.processaNovoPagamentoUseCase = processaNovoPagamentoUseCase;
        this.atualizaStatusPagamentoUsecase = atualizaStatusPagamentoUsecase;
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;

    }

    @Override //TODO implementar a chamda do enviaPagamentoUseCase onde terá a lógica da chamada do Gateway de Pgto
    public void enviaParaGatewayDePagamento(PagamentoAEnviarAoGatewayDTORequest pagamentoAEnviarAoGatewayDTORequest) {
        enviaPagamentoAoGatewayPagamentosUseCase.enviaRequestAoSistemaExternoPagamentos(getByCodigoPedido(pagamentoAEnviarAoGatewayDTORequest));

    }

    @Override
    public PagamentoAEnviarAoGatewayDTORequest preparaParaEnviarPagamentoAoGateway(PedidoAPagarDTORequest pedidoAPagarDTORequest) {
        processaNovoPagamentoUseCase.verificaSeJaExistePagamentoParaOPedido(getByCodigoPedido(pedidoAPagarDTORequest));
        atualizaStatusPagamentoUsecase.analisaStatusDoPagamento(pedidoAPagarDTORequest.getStatusPagamento(), StatusPagamento.IN_PROCESS, new Pagamento(pedidoAPagarDTORequest));
        atualizaStatusPagamentoUsecase.salvaStatus(getByCodigoPedido(pedidoAPagarDTORequest));
        return new PagamentoAEnviarAoGatewayDTORequest(getByCodigoPedido(pedidoAPagarDTORequest));
    }

    private Pagamento getByCodigoPedido(PedidoAPagarDTORequest pedidoAPagarDTORequest) {
        return buscaPagamentoUseCase.findByCodigoPedido(pedidoAPagarDTORequest.getCodigoPedido());
    }

    private Pagamento getByCodigoPedido(PagamentoAEnviarAoGatewayDTORequest pagamentoAEnviarAoGatewayDTORequest) {
        return buscaPagamentoUseCase.findByCodigoPedido(pagamentoAEnviarAoGatewayDTORequest.getCodigoPedido());
    }

}
