package br.fiap.projeto.contexto.pagamento.domain.port.repository;

import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepositoryPort {

    Optional<Pagamento> findByCodigoPedido(Long codigoPedido);
    
    void salvaPagamento(Pagamento pagamento);

    Pagamento findByCodigo(UUID codigo);

    void salvaStatus(Pagamento pagamento);

    Page<Pagamento> findAll(Pageable pageable);

    Page<Pagamento> findByStatusPagamento(StatusPagamento status, Pageable pageable);
}
