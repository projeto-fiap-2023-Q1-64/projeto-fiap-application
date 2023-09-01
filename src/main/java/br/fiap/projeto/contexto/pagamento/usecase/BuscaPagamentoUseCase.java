package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IBuscaPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Optional<Pagamento> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigo(codigo));
        possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Pagamento com código " + codigo + " inexistente."));
        return possivelPagamento.get();
    }

    @Override
    public List<Pagamento> findByStatusPagamento(StatusPagamento status) {
        return pagamentoAdapterGateway.findByStatusPagamento(status);
    }

    @Override
    public List<Pagamento> findByCodigoPedido(String codigoPedido) {
        Optional<List<Pagamento>> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigoPedido(codigoPedido));
        return possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + codigoPedido + " não foi encontrado."));
    }

    @Override
    public Pagamento findByCodigoPedidoNotRejected(String codigoPedido) {
        Optional<List<Pagamento>> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigoPedidoAndStatusPagamentoNot(codigoPedido, StatusPagamento.REJECTED));
        possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + codigoPedido + " não foi encontrado."));
        return possivelPagamento.get().stream().findFirst().get();
    }

    @Override
    public Pagamento findByCodigoPedidoRejected(String codigoPedido) {
        Optional<List<Pagamento>> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigoPedidoAndStatusPagamento(codigoPedido, StatusPagamento.REJECTED));
        possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + codigoPedido + " não foi encontrado."));
        return possivelPagamento.get().stream().findFirst().get();
    }
}
