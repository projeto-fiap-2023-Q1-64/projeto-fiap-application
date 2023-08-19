package br.fiap.projeto.contexto.pagamento.usecase.port.repository;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PedidoAPagarDTO;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepositoryPort {

    Pagamento findByCodigoPedido(String codigoPedido);

    Optional<PedidoAPagarDTO> findByCodigoPedidoAPagar(String codigoPedido);
    
    void salvaPagamento(Pagamento pagamento);

    Pagamento findByCodigo(UUID codigo);

    void salvaStatus(Pagamento pagamento);

    Page<Pagamento> findAll(Pageable pageable);

    Page<Pagamento> findByStatusPagamento(StatusPagamento status, Pageable pageable);

    List<Pagamento> findByStatusPagamento(StatusPagamento status);

    void salvaPedidosAPagar(Pagamento pedidosAPagar);

    List<Pagamento> findAllByCodigoPedido();


}
