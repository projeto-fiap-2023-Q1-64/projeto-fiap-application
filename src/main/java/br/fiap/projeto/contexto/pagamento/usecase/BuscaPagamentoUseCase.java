package br.fiap.projeto.contexto.pagamento.usecase;

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
        return possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
    }

    @Override
    public List<Pagamento> findByStatusPagamento(StatusPagamento status) {
        return pagamentoAdapterGateway.findByStatusPagamento(status);
    }

    @Override
    public Pagamento findByCodigoPedido(String codigoPedido) {
        Optional<Pagamento> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigoPedido(codigoPedido));
        return possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + codigoPedido + " não foi encontrado."));
    }
}
