package br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IBuscaPagamentoRestAdapterController {

    Page<PagamentoDTOResponse> findAll(Pageable pageable);
    PagamentoDTOResponse findByCodigo(UUID codigo);

    Page<PagamentoDTOResponse> findByStatusPagamento(StatusPagamento status, Pageable pageable);

    PagamentoDTOResponse findByCodigoPedido(String codigoPedido);


}
