package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.UnprocessablePaymentException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.mensagens.MensagemDeErro;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IEnviaPagamentoAoGatewayPagamentosUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IProcessaNovoPagamentoUseCase;

import java.util.NoSuchElementException;

public class EnviaPagamentoAoGatewayPagamentosUseCase implements IEnviaPagamentoAoGatewayPagamentosUseCase {

    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;

    private final IProcessaNovoPagamentoUseCase processaPagamentoUseCase;

    private final IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase;

    public EnviaPagamentoAoGatewayPagamentosUseCase(IBuscaPagamentoUseCase buscaPagamentoUseCase,
                                                    IProcessaNovoPagamentoUseCase processaNovoPagamentoUseCase,
                                                    IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase) {
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
        this.processaPagamentoUseCase = processaNovoPagamentoUseCase;
        this.atualizaStatusPagamentoUsecase = atualizaStatusPagamentoUsecase;
    }

   @Override
    public void enviaRequestAoSistemaExternoPagamentos(String codigoPedido, StatusPagamento status) {
        //TODO aqui faria as chamadas às integrações com o sistema externo - hoje apenas é simulado
        printMensagensSimulaIntegracaoComSistemaExterno(codigoPedido, status);
   }

    @Override
    public void verificaPagamentoAntesDeEnviarAoGateway(String codigoPedido, StatusPagamento status) {
        processaPagamentoUseCase.verificaSeJaExistePagamentoParaOPedido(getPagamento(codigoPedido));
        if(getPagamento(codigoPedido).getCodigo() == (null)){
            throw new ResourceNotFoundException(MensagemDeErro.PAGAMENTO_NAO_ENCONTRADO.getMessage());
        }
    }

    @Override
    public void validaStatusAtualDoPagamentoAntesDeEnviarAoGateway(String codigoPedido, StatusPagamento status) {
        Pagamento pagamentoStatusAtual = getPagamento(codigoPedido);
        if(!pagamentoStatusAtual.getStatus().equals(StatusPagamento.PENDING)){
            throw new UnprocessablePaymentException(MensagemDeErro.STATUS_INVALIDO_ENVIO_GATEWAY.getMessage());
        }
    }

    @Override
    public Pagamento atualizaStatusNovoAoEnviarPagamentoAoGateway(String codigoPedido, StatusPagamento statusPagamento) {
        Pagamento pagamentoAnalisado = getPagamento(codigoPedido);
        atualizaStatusPagamentoUsecase.analisaStatusDoPagamento(pagamentoAnalisado.getStatus(), statusPagamento, pagamentoAnalisado);
        atualizaStatusPagamentoUsecase.salvaStatus(pagamentoAnalisado);
        return pagamentoAnalisado;
    }

    private Pagamento getPagamento(String codigoPedido) {
        try {
            return buscaPagamentoUseCase.findByCodigoPedidoNotRejected(codigoPedido);
        }catch(NoSuchElementException elementException){
            throw new ResourceNotFoundException(elementException.getMessage());
        }
    }

    private static void printMensagensSimulaIntegracaoComSistemaExterno(String codigoPedido, StatusPagamento status) {
        System.out.println("Enviando pagamento ao Sistema Externo de Pagamentos: MercadoPago");
        System.out.println("Pagamento agora será processado pelo Gateway de Pagamento.");
        System.out.println("Pagamento do Pedido: " + codigoPedido);
        System.out.println("Status: " + status);
    }

}
