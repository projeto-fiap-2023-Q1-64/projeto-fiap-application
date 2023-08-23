package br.fiap.projeto.contexto.pagamento.adapter.gateway;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.external.repository.entity.PagamentoEntity;
import br.fiap.projeto.contexto.pagamento.external.repository.postgres.SpringPagamentoRepository;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IBuscaPagamentoRepositoryAdapterGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class BuscaPagamentoRepositoryAdapterGateway implements IBuscaPagamentoRepositoryAdapterGateway {

    private final SpringPagamentoRepository springPagamentoRepository;

    public BuscaPagamentoRepositoryAdapterGateway(SpringPagamentoRepository springPagamentoRepository) {
        this.springPagamentoRepository = springPagamentoRepository;
    }

    @Override
    public Page<Pagamento> findAll(Pageable pageable) {
        Page<PagamentoEntity> listaDePagamentos = springPagamentoRepository.findAll(pageable);
        return  listaDePagamentos.map(Pagamento::new);
    }

    @Override
    public Pagamento findByCodigo(UUID codigo) {
        return new Pagamento(springPagamentoRepository.findByCodigo(codigo));
    }

    @Override
    public Page<Pagamento> findByStatusPagamento(StatusPagamento status, Pageable pageable) {
        Page<PagamentoEntity> listaDePagamentos = springPagamentoRepository.findByStatusPagamento(status, pageable);
        return  listaDePagamentos.map(Pagamento::new);
    }

    @Override
    public Pagamento findByCodigoPedido(String codigoPedido) {
        return new Pagamento( springPagamentoRepository.findByCodigoPedido(codigoPedido));
    }
}
