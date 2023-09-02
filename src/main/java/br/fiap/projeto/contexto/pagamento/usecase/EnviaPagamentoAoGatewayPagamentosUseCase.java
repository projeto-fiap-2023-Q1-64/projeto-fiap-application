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

    private final IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase;

    public EnviaPagamentoAoGatewayPagamentosUseCase(IBuscaPagamentoUseCase buscaPagamentoUseCase,
                                                    IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase) {
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
        this.atualizaStatusPagamentoUsecase = atualizaStatusPagamentoUsecase;
    }

   @Override
    public void enviaRequestAoSistemaExternoPagamentos(String codigoPedido, StatusPagamento status) {
        //TODO aqui faria as chamadas às integrações com o sistema externo - hoje apenas é simulado
        printMensagensSimulaIntegracaoComSistemaExterno(codigoPedido, status);
   }

    @Override
    public Pagamento preparaParaEnviarPagamentoAoGateway(String codigoPedido) {
        this.verificaPagamentoAntesDeEnviarAoGateway(codigoPedido);
        this.validaStatusAtualDoPagamentoAntesDeEnviarAoGateway(codigoPedido);
        return this.atualizaStatusNovoAoEnviarPagamentoAoGateway(codigoPedido);
    }

    private Pagamento getPagamento(String codigoPedido) {
        try {
            return buscaPagamentoUseCase.findByCodigoPedidoPending(codigoPedido);
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
    private void verificaPagamentoAntesDeEnviarAoGateway(String codigoPedido) {
        if(getPagamento(codigoPedido).getCodigo() == (null)){
            throw new ResourceNotFoundException(MensagemDeErro.PAGAMENTO_NAO_ENCONTRADO.getMessage());
        }
    }

    private void validaStatusAtualDoPagamentoAntesDeEnviarAoGateway(String codigoPedido) {
        Pagamento pagamentoStatusAtual = getPagamento(codigoPedido);
        if(!pagamentoStatusAtual.getStatus().equals(StatusPagamento.PENDING)){
            throw new UnprocessablePaymentException(MensagemDeErro.STATUS_INVALIDO_ENVIO_GATEWAY.getMessage());
        }
    }

    private Pagamento atualizaStatusNovoAoEnviarPagamentoAoGateway(String codigoPedido) {
        Pagamento pagamentoAnalisado = getPagamento(codigoPedido);
        atualizaStatusPagamentoUsecase.atualizaStatusPagamentoGateway(codigoPedido, StatusPagamento.IN_PROCESS);
        return pagamentoAnalisado;
    }

}
