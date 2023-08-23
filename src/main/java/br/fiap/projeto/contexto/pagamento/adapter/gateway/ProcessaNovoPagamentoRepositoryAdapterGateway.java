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
        Pagamento possivelPagamento = getPossivelPagamento(pagamento);
        if(possivelPagamento.getCodigo() != null){
            throw new UnprocessablePaymentException("Já existe um pagamento para este pedido.");
        }
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
        if(!(possivelPagamento.getCodigoPedido().isEmpty()) || (possivelPagamento.getCodigoPedido() != null)) {
            throw new ResourceAlreadyInProcessException("Pedido:  " + pagamento.getCodigoPedido() + " já possui pagamento PENDENTE.");
        }
    }

    private Pagamento getPossivelPagamento(Pagamento pagamento) {
        return new Pagamento (springPagamentoRepository.findByCodigoPedido(pagamento.getCodigoPedido()));
    }

}
