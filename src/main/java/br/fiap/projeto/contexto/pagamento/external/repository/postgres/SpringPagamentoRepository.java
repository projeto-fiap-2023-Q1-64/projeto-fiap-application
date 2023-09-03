package br.fiap.projeto.contexto.pagamento.external.repository.postgres;

import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.external.repository.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringPagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {
    List<PagamentoEntity> findByCodigoPedido(String codigoPedido);

    List<PagamentoEntity> findByCodigoPedidoAndStatusPagamentoNot(String codigoPedido, StatusPagamento status);

    List<PagamentoEntity> findByCodigoPedidoAndStatusPagamento(String codigoPedido, StatusPagamento status);

    PagamentoEntity findByCodigo(UUID codigo);

    List<PagamentoEntity> findByStatusPagamento(StatusPagamento status);

}
