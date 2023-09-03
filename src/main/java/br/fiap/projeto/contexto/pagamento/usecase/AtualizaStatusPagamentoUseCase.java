package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.UnprocessablePaymentException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.mensagens.MensagemDeErro;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IAtualizaStatusPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IPagamentoPedidoIntegrationUseCase;

public class AtualizaStatusPagamentoUseCase implements IAtualizaStatusPagamentoUsecase {

    private final IAtualizaStatusPagamentoRepositoryAdapterGateway atualizaStatusPagamentoAdapterGateway;
    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;

    private final IPagamentoPedidoIntegrationUseCase pagamentoPedidoIntegrationUseCase;

    public AtualizaStatusPagamentoUseCase(IAtualizaStatusPagamentoRepositoryAdapterGateway atualizaStatusPagamentoAdapterGateway,
                                          IBuscaPagamentoUseCase buscaPagamentoUseCase,
                                          IPagamentoPedidoIntegrationUseCase pagamentoPedidoIntegrationUseCase) {
        this.atualizaStatusPagamentoAdapterGateway = atualizaStatusPagamentoAdapterGateway;
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
        this.pagamentoPedidoIntegrationUseCase = pagamentoPedidoIntegrationUseCase;
    }

    /**
     * Utilizado pelo retorno do Gateway de Pagamentos para atualizar o Pagamento
     * com a resposta do sistema bancário externo. Compara o status passado na request
     * (novo status) com o status existente do pagamento, as possíveis transições de estado são:
     * <li>IN_PROCESS -> APPROVED ou CANCELLED</li>
     * <li>REJECTED -> CANCELLED</li>
     * <li>PENDING ->  IN_PROCESS somente via atualização de status pelo método de envio ao gateway, não deve ser atualizado aqui</li>
     *
     * @param codigoPedido
     * @param statusPagamento
     */
    @Override
    public void atualizaStatusPagamento(String codigoPedido, StatusPagamento novoStatusPagamento) {
        Pagamento pagamento;
        switch (novoStatusPagamento){
            case CANCELLED:
                pagamento = buscaPagamentoUseCase.findByCodigoPedidoRejected(codigoPedido);
                pagamento.cancelaPagamento(pagamento);
                break;
            case REJECTED:
                pagamento = buscaPagamentoUseCase.findByCodigoPedidoInProcess(codigoPedido);
                pagamento.rejeitaPagamento(pagamento);
                break;
            case APPROVED:
                pagamento = buscaPagamentoUseCase.findByCodigoPedidoInProcess(codigoPedido);
                pagamento.aprovaPagamento(pagamento);
                break;
            case IN_PROCESS:
                throw new UnprocessablePaymentException(MensagemDeErro.PAGAMENTO_DEVE_SER_ENVIADO_AO_GATEWAY.getMessage());
            case PENDING:
                throw new UnprocessablePaymentException(MensagemDeErro.PAGAMENTO_PENDENTE.getMessage());
            default:
                return;
        }
        salvaStatus(pagamento);

        if(novoStatusPagamento.equals(StatusPagamento.APPROVED) || novoStatusPagamento.equals(StatusPagamento.CANCELLED)) {
            triggerAtualizaStatusPagamentoDoPedido(pagamento.getCodigoPedido());
        }
    }

    private void triggerAtualizaStatusPagamentoDoPedido(String codigoPedido) {
        {
            System.out.println("TASK AGENDADA: ATUALIZA STATUS DO PAGAMENTO DO PEDIDO");
            pagamentoPedidoIntegrationUseCase.scheduleAtualizaPagamentoPedido(codigoPedido);
        }
    }

    /**
     * Utilizado para atualizar o status do pagamento ao ser enviado para o Gateway de Pagamentos.
     * A única transição entre estados possíveis é:
     * <li>PENDING -> IN_PROCESS</li>
     * Atualização de pendente para em processamento deverá acontecer somente ao
     * enviar par ao Gateway de Pagamentos.
     * @param codigoPedido
     * @param statusPagamento
     */
    @Override
    public void atualizaStatusPagamentoGateway(String codigoPedido, StatusPagamento statusPagamento) {
        Pagamento pagamento = buscaPagamentoUseCase.findByCodigoPedidoPending(codigoPedido);
        if(!pagamento.podeSerProcessado(pagamento.getStatus(), statusPagamento)){
            throw new UnprocessablePaymentException(MensagemDeErro.STATUS_INVALIDO.getMessage());
        }
        System.out.println("Pode ser processado ");
        pagamento.colocaEmProcessamento(pagamento);
        salvaStatus(pagamento);
    }
    private void salvaStatus(Pagamento pagamento) {
        atualizaStatusPagamentoAdapterGateway.atualizaStatusPagamento(pagamento);
    }
}