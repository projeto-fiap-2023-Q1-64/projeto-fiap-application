package br.fiap.projeto.contexto.pagamento.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pagamento.application.rest.response.PedidoAPagarDTO;
import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.domain.port.repository.PagamentoRepositoryPort;
import br.fiap.projeto.contexto.pagamento.infrastructure.entity.PagamentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
@Primary
public class PostgresPagamentoRepository implements PagamentoRepositoryPort {

    private final SpringPagamentoRepository springPagamentoRepository;

    @Autowired
    public PostgresPagamentoRepository(SpringPagamentoRepository springPagamentoRepository) {
        this.springPagamentoRepository = springPagamentoRepository;
    }

    @Override
    public Pagamento findByCodigoPedido(String codigoPedido) {
        PagamentoEntity pagamentoEntity = springPagamentoRepository.findByCodigoPedido(codigoPedido);
        return new Pagamento(pagamentoEntity);
    }

    @Override
    public Optional<PedidoAPagarDTO> findByCodigoPedidoAPagar(String codigoPedido) {
        PagamentoEntity pagamentoEntity = springPagamentoRepository.findByCodigoPedido(codigoPedido);
        return Optional.of(new PedidoAPagarDTO(new Pagamento(pagamentoEntity)));
    }

    @Override
    public void salvaPagamento(Pagamento pagamento) {
        springPagamentoRepository.save(new PagamentoEntity(pagamento));
    }

    @Override
    public Pagamento findByCodigo(UUID codigo) {
        PagamentoEntity pagamentoEntity = springPagamentoRepository.findByCodigo(codigo);
        return new Pagamento(pagamentoEntity);
    }

    @Override
    public void salvaStatus(Pagamento pagamento) {
        springPagamentoRepository.save(new PagamentoEntity(pagamento));
    }

    @Override
    public Page<Pagamento> findAll(Pageable pageable) {
        Page<PagamentoEntity> listaDePagamentos = springPagamentoRepository.findAll(pageable);
        return  listaDePagamentos.map(Pagamento::new);
    }

    @Override
    public Page<Pagamento> findByStatusPagamento(StatusPagamento status, Pageable pageable) {
        Page<PagamentoEntity> listaDePagamentos = springPagamentoRepository.findByStatusPagamento(status, pageable);
        return  listaDePagamentos.map(Pagamento::new);
    }
    public List<Pagamento> findByStatusPagamento(StatusPagamento status){
        List<PagamentoEntity> listaDePagamentos = springPagamentoRepository.findByStatusPagamento(status);
        return listaDePagamentos.stream().map(Pagamento::new).collect(Collectors.toList());
    }

    @Override
    public void salvaPedidosAPagar(Pagamento pedidosAPagar) {
        System.out.println("REPOSITORY: Salvando um pagamento para o pedido: " + pedidosAPagar.getCodigoPedido());
        springPagamentoRepository.save(new PagamentoEntity(pedidosAPagar));
    }

    @Override
    public List<Pagamento> findAllByCodigoPedido() {
        List<PagamentoEntity> listaDePedidosAPagar = springPagamentoRepository.findAll();
        return listaDePedidosAPagar.stream().map(Pagamento::new).collect(Collectors.toList());
    }


}
