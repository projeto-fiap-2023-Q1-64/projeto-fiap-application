package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IBuscaPagamentoRepositoryAdapterGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public class BuscaPagamentoUseCase implements IBuscaPagamentoUseCase {

    private final IBuscaPagamentoRepositoryAdapterGateway pagamentoAdapterGateway;
    public BuscaPagamentoUseCase(IBuscaPagamentoRepositoryAdapterGateway pagamentoAdapterGateway) {
        this.pagamentoAdapterGateway = pagamentoAdapterGateway;
    }

    @Override
    public Page<Pagamento> findAll(Pageable pageable) {
        return pagamentoAdapterGateway.findAll(pageable);
    }

    @Override
    public Pagamento findByCodigo(UUID codigo) {
        Optional<Pagamento> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigo(codigo));
        return possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
    }

    @Override
    public Page<Pagamento> findByStatusPagamento(StatusPagamento status, Pageable pageable) {
        return pagamentoAdapterGateway.findByStatusPagamento(status, pageable);
    }

    @Override
    public Pagamento findByCodigoPedido(String codigoPedido) {
        Optional<Pagamento> possivelPagamento = Optional.ofNullable(pagamentoAdapterGateway.findByCodigoPedido(codigoPedido));
        return possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + codigoPedido + " não foi encontrado."));
    }
}
