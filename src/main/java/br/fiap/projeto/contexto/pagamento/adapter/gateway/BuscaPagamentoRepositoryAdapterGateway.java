package br.fiap.projeto.contexto.pagamento.adapter.gateway;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.external.repository.entity.PagamentoEntity;
import br.fiap.projeto.contexto.pagamento.external.repository.postgres.SpringPagamentoRepository;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IBuscaPagamentoRepositoryAdapterGateway;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuscaPagamentoRepositoryAdapterGateway implements IBuscaPagamentoRepositoryAdapterGateway {

    private final SpringPagamentoRepository springPagamentoRepository;

    public BuscaPagamentoRepositoryAdapterGateway(SpringPagamentoRepository springPagamentoRepository) {
        this.springPagamentoRepository = springPagamentoRepository;
    }

    @Override
    public List<Pagamento> findAll() {
        List<PagamentoEntity> listaDePagamentos = springPagamentoRepository.findAll();
        return  listaDePagamentos.stream().map(PagamentoEntity::conversorDePagamentoORMEntityParaPagamentoDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Pagamento findByCodigo(UUID codigo) {
        PagamentoEntity pagamentoEntity = springPagamentoRepository.findByCodigo(codigo);
        return pagamentoEntity.conversorDePagamentoORMEntityParaPagamentoDomainEntity();
    }

    @Override
    public List<Pagamento> findByStatusPagamento(StatusPagamento status) {
        List<PagamentoEntity> listaDePagamentos = springPagamentoRepository.findByStatusPagamento(status);
        return  listaDePagamentos.stream().map(PagamentoEntity::conversorDePagamentoORMEntityParaPagamentoDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Pagamento> findByCodigoPedido(String codigoPedido) {
        List<PagamentoEntity> listaPagamentoEntity = springPagamentoRepository.findByCodigoPedido(codigoPedido);
        return listaPagamentoEntity.stream().map(PagamentoEntity::conversorDePagamentoORMEntityParaPagamentoDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Pagamento> findByCodigoPedidoAndStatusPagamentoNot(String codigoPedido, StatusPagamento status) {
        List<PagamentoEntity> listaPagamentoEntity = springPagamentoRepository.findByCodigoPedidoAndStatusPagamentoNot(codigoPedido, status);
        return listaPagamentoEntity.stream().map(PagamentoEntity::conversorDePagamentoORMEntityParaPagamentoDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Pagamento> findByCodigoPedidoAndStatusPagamento(String codigoPedido, StatusPagamento status) {
        List<PagamentoEntity> listaPagamentoEntity = springPagamentoRepository.findByCodigoPedidoAndStatusPagamento(codigoPedido, status);
        return listaPagamentoEntity.stream().map(PagamentoEntity::conversorDePagamentoORMEntityParaPagamentoDomainEntity).collect(Collectors.toList());
    }
}
