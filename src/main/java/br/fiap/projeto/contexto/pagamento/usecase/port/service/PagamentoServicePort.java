package br.fiap.projeto.contexto.pagamento.usecase.port.service;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PedidoAPagarDTO;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoAprovadoDTO;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTO;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface PagamentoServicePort {

    PagamentoDTO criaPagamento(PagamentoDTO pagamentoDTO) throws Exception;

    PedidoAPagarDTO criaPagamentoViaGateway(PedidoAPagarDTO pedidoAPagarDTO);

    void lidaStatusPagamento(StatusPagamento statusAtual, PagamentoDTO pagamentoDTO, StatusPagamento statusRequest);

    void processaPagamento(UUID codigo, StatusPagamento status);

    Page<PagamentoDTO> buscaListaDePagamentos(Pageable pageable);

    PagamentoDTO findByCodigo(UUID codigo);

    PagamentoDTO findByCodigoPedido(String codigoPedido);

    void verificaNumeroDoPedido(PagamentoDTO pagamentoDTO);

    Page<PagamentoDTO> findByStatus(StatusPagamento status, Pageable pageable);

    Page<PagamentoAprovadoDTO> findByStatusAprovado(Pageable pageable);

    void enviaGatewayDePagamento(PedidoAPagarDTO pedidoAPagarDTOApagar);

    void recebePedidosAPagar(PedidoAPagarDTO paginaDePedidosAPagar);

    List<PedidoAPagarDTO> buscaPedidosAPagar();
}
