package br.fiap.projeto.contexto.pagamento.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.infrastructure.entity.PagamentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringPagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {
    PagamentoEntity findByCodigoPedido(String codigoPedido);

    PagamentoEntity findByCodigo(UUID codigo);

    Page<PagamentoEntity> findByStatusPagamento(StatusPagamento status, Pageable pageable);

    List<PagamentoEntity> findByStatusPagamento(StatusPagamento status);
    /**
     * Teste para verificar consumo dos pedidos a pagar via API Pedidos
     *
     * @return
     */
    List<PagamentoEntity> findAll();


}
