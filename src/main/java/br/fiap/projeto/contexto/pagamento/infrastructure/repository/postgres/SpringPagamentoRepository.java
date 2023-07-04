package br.fiap.projeto.contexto.pagamento.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.infrastructure.entity.PagamentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringPagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {
    Optional<Pagamento> findByCodigoPedido(Long codigoPedido);

    Pagamento findByCodigo(UUID codigo);

    Page<PagamentoEntity> findByStatusPagamento(StatusPagamento status, Pageable pageable);

    // Pagamento save(Pagamento novoPagamento); tá na jpa

}
