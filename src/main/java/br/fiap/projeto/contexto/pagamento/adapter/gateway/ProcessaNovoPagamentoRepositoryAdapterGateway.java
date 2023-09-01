package br.fiap.projeto.contexto.pagamento.adapter.gateway;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.external.repository.entity.PagamentoEntity;
import br.fiap.projeto.contexto.pagamento.external.repository.postgres.SpringPagamentoRepository;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceAlreadyInProcessException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IProcessaNovoPagamentoRepositoryAdapterGateway;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class ProcessaNovoPagamentoRepositoryAdapterGateway implements IProcessaNovoPagamentoRepositoryAdapterGateway {

    private final SpringPagamentoRepository springPagamentoRepository;

    public ProcessaNovoPagamentoRepositoryAdapterGateway(SpringPagamentoRepository springPagamentoRepository) {
        this.springPagamentoRepository = springPagamentoRepository;
    }

    @Override
    public Pagamento salvaNovoPagamento(Pagamento pagamento) {
        springPagamentoRepository.save(new PagamentoEntity(pagamento));
        return getPagamento(pagamento);
    }

    //TODO verificar se aqui poderia ser alterado pra recriar um pagamento de um pedido Rejeitado
    @Override
    public Pagamento verificaCondicoesParaCriarPagamento(Pagamento pagamento) {
        Pagamento possivelPagamento = getPossivelPagamento(pagamento);
        if(possivelPagamento.getStatus().equals(StatusPagamento.REJECTED)){
            return possivelPagamento;
        }
        throw new ResourceAlreadyInProcessException("Pedido:  " + pagamento.getCodigoPedido() + " já possui pagamento. INFO: " + possivelPagamento);
    }

    /**
     * Um pagamento poderá ser criado quando:<br>
     * <li>Não existir um código de pagamento</li><br>
     * <li>Existir um código de pagamento mas o Status de Pagamento se encontrar em REJECTED</li>
     * <br>Para os casos em que o pagamento está Aprovado e Cancelado, não deverá permitir o fluxo
     * desta criação de pagamentos.
     *
     * @param pagamento
     * @return
     */
    @Override
    public Boolean verificaSeJaExistePagamentoParaOPedido(Pagamento pagamento) {

        Pagamento possivelPagamento = getPossivelPagamento(pagamento);
        if(possivelPagamento == null){
            System.out.println("Não existe pagamento para esse código de pedido");
            return false;
        }
        //TODO validar a regra para Status Pending após a entrada do Rejected
        if(possivelPagamento.getStatus().equals(StatusPagamento.PENDING) || possivelPagamento.getStatus().equals(StatusPagamento.REJECTED)) {
            System.out.println("Existe um pagamento e ele está no status: " + possivelPagamento.getStatus());
            return true;
        }else{
            throw new ResourceAlreadyInProcessException("Pedido:  " + pagamento.getCodigoPedido() + " já possui pagamento. INFO: " + possivelPagamento);
        }
    }

    private Pagamento getPossivelPagamento(Pagamento pagamento) {
        if(pagamento.getCodigoPedido() == null){
            throw new ResourceNotFoundException("Código do Pedido inválido");
        }
        return getPagamento(pagamento);
    }
    private Pagamento getPagamento(Pagamento pagamento) {
        List<PagamentoEntity> listaPagamentoEntity = springPagamentoRepository.findByCodigoPedido(pagamento.getCodigoPedido());

        try {
            Optional<PagamentoEntity> possivelPagamentoExistente = listaPagamentoEntity
                    .stream()
                    .filter(pagamentoEntity -> Objects.equals(
                            pagamentoEntity.getCodigoPedido(),
                            pagamento.getCodigoPedido()))
                    .findFirst();
            return possivelPagamentoExistente.get().conversorDePagamentoORMEntityParaPagamentoDomainEntity();
        }catch(NoSuchElementException elementException) {
            return null;
        }
    }
}
