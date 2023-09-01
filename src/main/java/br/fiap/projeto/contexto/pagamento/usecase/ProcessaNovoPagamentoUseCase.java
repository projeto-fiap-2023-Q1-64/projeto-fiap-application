package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceAlreadyInProcessException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.mensagens.MensagemDeErro;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IProcessaNovoPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IProcessaNovoPagamentoUseCase;

import java.util.NoSuchElementException;

public class ProcessaNovoPagamentoUseCase implements IProcessaNovoPagamentoUseCase {

    private final IProcessaNovoPagamentoRepositoryAdapterGateway processaNovoPagamentoAdapterGateway;
    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;

    public ProcessaNovoPagamentoUseCase(IProcessaNovoPagamentoRepositoryAdapterGateway processaNovoPagamentoAdapterGateway, IBuscaPagamentoUseCase buscaPagamentoUseCase) {
        this.processaNovoPagamentoAdapterGateway = processaNovoPagamentoAdapterGateway;
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
    }

    /**
     *  Um pagamento poderá ser criado quando:<br>
     *  <li>Não existir um código de pagamento</li><br>
     *  <li>Existir um código de pagamento mas o Status de Pagamento se encontrar em REJECTED</li>
     *  <br>Para os casos em que o pagamento está Aprovado e Cancelado, não deverá permitir o fluxo
     *  desta criação de pagamentos.
     * @param pagamento
     * @return
     */

    @Override
    public Pagamento criaNovoPagamento(Pagamento pagamento) {
       //verifica se existe pagamento
        Pagamento possivelPagamento = verificaSeJaExistePagamentoParaOPedido(pagamento);
        //se não existe ou possui status rejected, cria um pagamento
        if(possivelPagamento == null || possivelPagamento.getStatus().equals(StatusPagamento.REJECTED)){
            processaNovoPagamentoAdapterGateway.salvaNovoPagamento(pagamento);
            System.out.println("USE CASE: Novo pagamento criado para o pedido: " + pagamento.getCodigoPedido());
            return pagamento;
        }else{
            throw new ResourceAlreadyInProcessException(MensagemDeErro.PAGAMENTO_EXISTENTE.getMessage());
        }
    }


    @Override
    public Pagamento verificaSeJaExistePagamentoParaOPedido(Pagamento pagamento) {
        try {
            return buscaPagamentoUseCase
                    .findByCodigoPedido(pagamento.getCodigoPedido())
                    .stream()
                    .filter(p -> p.getCodigoPedido()
                            .equals(pagamento.getCodigoPedido())).findFirst().get();
        }catch(NoSuchElementException elementException){
            return null;
        }
    }
}
