package br.fiap.projeto.contexto.pagamento.domain.port.repository;

import br.fiap.projeto.contexto.pagamento.application.rest.response.PedidoAPagarDTO;
import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepositoryPort {

    Pagamento findByCodigoPedido(String codigoPedido);
    
    void salvaPagamento(Pagamento pagamento);

    Pagamento findByCodigo(UUID codigo);

    void salvaStatus(Pagamento pagamento);

    Page<Pagamento> findAll(Pageable pageable);

    Page<Pagamento> findByStatusPagamento(StatusPagamento status, Pageable pageable);

    List<Pagamento> findByStatusPagamento(StatusPagamento status);
    void salvaPedidosAPagar(Pagamento pedidosAPagar);

    List<Pagamento> findAllByCodigoPedido();
}
