package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.mensagens.MensagemDeErro;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IBuscaPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;

import java.util.*;

public class BuscaPagamentoUseCase implements IBuscaPagamentoUseCase {

    private final IBuscaPagamentoRepositoryAdapterGateway pagamentoAdapterGateway;
    public BuscaPagamentoUseCase(IBuscaPagamentoRepositoryAdapterGateway pagamentoAdapterGateway) {
        this.pagamentoAdapterGateway = pagamentoAdapterGateway;
    }

    @Override
    public List<Pagamento> findAll() {
        return pagamentoAdapterGateway.findAll();
    }

    @Override
    public Pagamento findByCodigo(UUID codigo) {
        try{
            Optional<Pagamento> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigo(codigo));
            return possivelPagamento.get();
        }catch(Exception e){
            throw new ResourceNotFoundException(MensagemDeErro.PAGAMENTO_NAO_ENCONTRADO.getMessage());
        }
    }

    @Override
    public List<Pagamento> findByStatusPagamento(StatusPagamento status) {
        return pagamentoAdapterGateway.findByStatusPagamento(status);
    }

    @Override
    public List<Pagamento> findByCodigoPedido(String codigoPedido) {
        try{
            Optional<List<Pagamento>> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigoPedido(codigoPedido));
            return possivelPagamento.get();
        }catch(NoSuchElementException elementException){
            throw new ResourceNotFoundException(MensagemDeErro.PEDIDO_PAGAMENTO_NAO_ENCONTRADO.getMessage());
        }
    }

    @Override
    public Pagamento findByCodigoPedidoNotRejected(String codigoPedido) {
        try{
            Optional<List<Pagamento>> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigoPedidoAndStatusPagamentoNot(codigoPedido, StatusPagamento.REJECTED));
            return possivelPagamento.get().stream().findFirst().get();
        }catch(NoSuchElementException elementException){
            throw new ResourceNotFoundException(MensagemDeErro.PEDIDO_PAGAMENTO_NAO_ENCONTRADO.getMessage());
        }
    }

    @Override
    public Pagamento findByCodigoPedidoRejected(String codigoPedido) {
        try{
            Optional<List<Pagamento>> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigoPedidoAndStatusPagamento(codigoPedido, StatusPagamento.REJECTED));
            return possivelPagamento.get().stream().findFirst().get();
        }catch(NoSuchElementException elementException){
            throw new ResourceNotFoundException(MensagemDeErro.PEDIDO_PAGAMENTO_NAO_ENCONTRADO.getMessage());
        }
    }
    @Override
    public Pagamento findByCodigoPedidoPending(String codigoPedido) {
        try{
            Optional<List<Pagamento>> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigoPedidoAndStatusPagamento(codigoPedido, StatusPagamento.PENDING));
            return possivelPagamento.get().stream().findFirst().get();
        }catch(NoSuchElementException elementException){
            throw new ResourceNotFoundException(MensagemDeErro.PEDIDO_PAGAMENTO_NAO_ENCONTRADO.getMessage());
        }
    }
}
