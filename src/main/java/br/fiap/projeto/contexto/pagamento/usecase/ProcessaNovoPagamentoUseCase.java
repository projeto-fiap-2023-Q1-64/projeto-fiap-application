package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IProcessaNovoPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IProcessaNovoPagamentoUseCase;

public class ProcessaNovoPagamentoUseCase implements IProcessaNovoPagamentoUseCase {

    private final IProcessaNovoPagamentoRepositoryAdapterGateway processaNovoPagamentoAdapterGateway;

    public ProcessaNovoPagamentoUseCase(IProcessaNovoPagamentoRepositoryAdapterGateway processaNovoPagamentoAdapterGateway) {
        this.processaNovoPagamentoAdapterGateway = processaNovoPagamentoAdapterGateway;
    }

    //TODO remover cmt após testes - NOTA: antigo salvaPedidosAPagar : deverão ser recebidos por este UseCase do cntx de Pedidos
    @Override
    public void criaNovoPagamento(Pagamento pagamento) {
        processaNovoPagamentoAdapterGateway.verificaSeJaExistePagamentoParaOPedido(pagamento);
        processaNovoPagamentoAdapterGateway.verificaCondicoesParaCriarPagamento(pagamento);
        processaNovoPagamentoAdapterGateway.salvaNovoPagamento(pagamento);
        System.out.println("USE CASE: Novo pagamento criado para o pedido: " + pagamento.getCodigoPedido());
    }

    @Override
    public void verificaSeJaExistePagamentoParaOPedido(Pagamento pagamento) {
        processaNovoPagamentoAdapterGateway.verificaSeJaExistePagamentoParaOPedido(pagamento);
    }

    @Override
    public void verificaCondicoesParaCriarPagamento(Pagamento pagamento) {
        processaNovoPagamentoAdapterGateway.verificaCondicoesParaCriarPagamento(pagamento);
    }


}
