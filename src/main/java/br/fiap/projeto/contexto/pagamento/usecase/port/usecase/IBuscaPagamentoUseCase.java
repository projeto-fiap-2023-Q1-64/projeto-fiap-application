package br.fiap.projeto.contexto.pagamento.usecase.port.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

import java.util.List;
import java.util.UUID;

public interface IBuscaPagamentoUseCase {

    List<Pagamento> findAll();

    Pagamento findByCodigo(UUID codigo);

    List<Pagamento> findByStatusPagamento(StatusPagamento status);

    List<Pagamento> findByCodigoPedido(String codigoPedido);


}
