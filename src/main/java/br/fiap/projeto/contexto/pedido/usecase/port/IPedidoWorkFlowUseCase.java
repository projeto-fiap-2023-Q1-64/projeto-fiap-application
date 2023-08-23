package br.fiap.projeto.contexto.pedido.usecase.port;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;

import java.util.UUID;

public interface IPedidoWorkFlowUseCase {
    PedidoDTO receber(UUID codigo) throws Exception;
    PedidoDTO pagar(UUID codigo) throws Exception;
    PedidoDTO preparar(UUID codigo) throws Exception;
    PedidoDTO prontificar(UUID codigo) throws Exception;
    PedidoDTO finalizar(UUID codigo) throws Exception;
}
