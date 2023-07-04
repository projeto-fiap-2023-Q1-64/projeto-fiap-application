package br.fiap.projeto.contexto.pagamento.domain.port.service;

import br.fiap.projeto.contexto.pagamento.application.rest.response.CompraAPagarDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoAprovadoDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoDTO;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface PagamentoServicePort {

    PagamentoDTO criaPagamento(PagamentoDTO pagamentoDTO) throws Exception;

    void lidaStatusPagamento(StatusPagamento statusAtual, PagamentoDTO pagamentoDTO, StatusPagamento statusRequest);

    void processaPagamento(UUID codigo, StatusPagamento status);

    Page<PagamentoDTO> buscaListaDePagamentos(Pageable pageable);

    PagamentoDTO findByCodigo(UUID codigo);

    PagamentoDTO findByCodigoPedido(Long codigoPedido);

    void verificaNumeroDoPedido(PagamentoDTO pagamentoDTO);

    Page<PagamentoDTO> findByStatus(StatusPagamento status, Pageable pageable);

    Page<PagamentoAprovadoDTO> findByStatusAprovado(Pageable pageable);

    void enviaGatewayDePagamento(CompraAPagarDTO compraApagar);


}
