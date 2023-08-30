package br.fiap.projeto.contexto.pagamento.adapter.gateway;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.external.repository.entity.PagamentoEntity;
import br.fiap.projeto.contexto.pagamento.external.repository.postgres.SpringPagamentoRepository;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceAlreadyInProcessException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.UnprocessablePaymentException;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IProcessaNovoPagamentoRepositoryAdapterGateway;

public class ProcessaNovoPagamentoRepositoryAdapterGateway implements IProcessaNovoPagamentoRepositoryAdapterGateway {

    private final SpringPagamentoRepository springPagamentoRepository;

    public ProcessaNovoPagamentoRepositoryAdapterGateway(SpringPagamentoRepository springPagamentoRepository) {
        this.springPagamentoRepository = springPagamentoRepository;
    }

    @Override
    public void salvaNovoPagamento(Pagamento pagamento) {
        //TODO não está sendo verificado se já existe em banco um pagamento pra esse que está a ser salvo
        springPagamentoRepository.save(new PagamentoEntity(pagamento));
    }

    @Override
    public void verificaCondicoesParaCriarPagamento(Pagamento pagamento) {
        Pagamento possivelPagamento = getPossivelPagamento(pagamento);
        if(!possivelPagamento.getStatus().equals(StatusPagamento.PENDING)){
            throw new UnprocessablePaymentException("Não foi possível iniciar pagamento para este pedido.");
        }

        if(possivelPagamento.getCodigoPedido() == null){
            throw new ResourceNotFoundException("Código do pedido inexistente");
        }
    }

    @Override
    public void verificaSeJaExistePagamentoParaOPedido(Pagamento pagamento) {
        Pagamento possivelPagamento = getPossivelPagamento(pagamento);
        //INFO será enviado ao Gateway de Pagamentos se estiver com Pending, qualquer outro status deverá ser rejeitado
        if(!possivelPagamento.getStatus().equals(StatusPagamento.PENDING)) {
            throw new ResourceAlreadyInProcessException("Pedido:  " + pagamento.getCodigoPedido() + " já possui pagamento. INFO: " + possivelPagamento);
        }
    }

    private Pagamento getPossivelPagamento(Pagamento pagamento) {
        if(pagamento.getCodigoPedido() == null){
            throw new ResourceNotFoundException("Código do Pedido inválido");
        }
        PagamentoEntity pagamentoEntity = springPagamentoRepository.findByCodigoPedido(pagamento.getCodigoPedido());
        return pagamentoEntity.conversorDePagamentoORMEntityParaPagamentoDomainEntity();
    }

}
