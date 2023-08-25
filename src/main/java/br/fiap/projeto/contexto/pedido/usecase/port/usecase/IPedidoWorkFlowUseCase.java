package br.fiap.projeto.contexto.pedido.usecase.port.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;

import java.util.UUID;

public interface IPedidoWorkFlowUseCase {
    Pedido receber(UUID codigo) throws Exception;
    Pedido pagar(UUID codigo) throws Exception;
    Pedido preparar(UUID codigo) throws Exception;
    Pedido prontificar(UUID codigo) throws Exception;
    Pedido finalizar(UUID codigo) throws Exception;
}
