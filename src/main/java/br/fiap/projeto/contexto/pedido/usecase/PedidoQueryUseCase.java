package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoQueryUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoRepositoryAdapterGateway;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoQueryUseCase extends AbstractPedidoUseCase implements IPedidoQueryUseCase {
    public PedidoQueryUseCase(IPedidoRepositoryAdapterGateway IPedidoRepositoryAdapterGateway) {
        super(IPedidoRepositoryAdapterGateway);
    }
    @Override
    public PedidoDTO buscaPedido(UUID codigo) {
        return this.buscar(codigo).toPedidoDTO();
    }
    @Override
    public List<PedidoDTO> buscaTodos() {
        List<Pedido> pedidoLista = IPedidoRepositoryAdapterGateway.buscaTodos();
        return pedidoLista.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    @Override
    public List<PedidoDTO> buscarTodosRecebido(){
        List<Pedido> pedidosRecebidos = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.RECEBIDO);
        return pedidosRecebidos.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    @Override
    public List<PedidoDTO> buscarTodosPagos(){
        List<Pedido> pedidosRecebidos = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.PAGO);
        return pedidosRecebidos.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    @Override
    public List<PedidoDTO> buscarTodosEmPreparacao(){
        List<Pedido> pedidosRecebidos = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.EM_PREPARACAO);
        return pedidosRecebidos.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    @Override
    public List<PedidoDTO> buscarTodosPronto(){
        List<Pedido> pedidosRecebidos = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.PRONTO);
        return pedidosRecebidos.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    @Override
    public List<PedidoDTO> buscarTodosFinalizado(){
        List<Pedido> pedidosRecebidos = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.FINALIZADO);
        return pedidosRecebidos.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
}
