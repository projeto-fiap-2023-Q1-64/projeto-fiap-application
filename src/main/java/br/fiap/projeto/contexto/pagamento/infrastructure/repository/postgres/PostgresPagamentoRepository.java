package br.fiap.projeto.contexto.pagamento.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.domain.port.repository.PagamentoRepositoryPort;
import br.fiap.projeto.contexto.pagamento.infrastructure.entity.PagamentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
@Primary
public class PostgresPagamentoRepository implements PagamentoRepositoryPort {

    private final SpringPagamentoRepository springPagamentoRepository;

    @Autowired
    public PostgresPagamentoRepository(SpringPagamentoRepository springPagamentoRepository) {
        this.springPagamentoRepository = springPagamentoRepository;
    }

    @Override
    public Optional<Pagamento> findByCodigoPedido(Long codigoPedido) {
       return springPagamentoRepository.findByCodigoPedido(codigoPedido);
    }
    @Override
    public void salvaPagamento(Pagamento pagamento) {
        springPagamentoRepository.save(new PagamentoEntity(pagamento));
    }

    @Override
    public Pagamento findByCodigo(UUID codigo) {
        return springPagamentoRepository.findByCodigo(codigo);
    }

    @Override
    public void salvaStatus(Pagamento pagamento) {
        springPagamentoRepository.save(new PagamentoEntity(pagamento));
    }

    @Override
    public Page<Pagamento> findAll(Pageable pageable) {
        Page<PagamentoEntity> listaDePagamentos = springPagamentoRepository.findAll(pageable);
        return  listaDePagamentos.map(x -> new Pagamento(x));
    }

    @Override
    public Page<Pagamento> findByStatusPagamento(StatusPagamento status, Pageable pageable) {
        Page<PagamentoEntity> listaDePagamentos = springPagamentoRepository.findByStatusPagamento(status, pageable);
        return  listaDePagamentos.map(x -> new Pagamento(x));
    }


}
