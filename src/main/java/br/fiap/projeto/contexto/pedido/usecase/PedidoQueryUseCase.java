package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoQueryUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoRepositoryAdapterGateway;

import java.util.List;

public class PedidoQueryUseCase extends AbstractPedidoUseCase implements IPedidoQueryUseCase {
    public PedidoQueryUseCase(IPedidoRepositoryAdapterGateway IPedidoRepositoryAdapterGateway) {
        super(IPedidoRepositoryAdapterGateway);
    }
    @Override
    public List<Pedido> buscaTodos() {
        List<Pedido> pedidoLista = IPedidoRepositoryAdapterGateway.buscaTodos();
        return pedidoLista;
    }
    @Override
    public List<Pedido> buscarTodosRecebido(){
        List<Pedido> pedidosRecebidos = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.RECEBIDO);
        return pedidosRecebidos;
    }
    @Override
    public List<Pedido> buscarTodosPagos(){
        List<Pedido> pedidosRecebidos = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.PAGO);
        return pedidosRecebidos;
    }
    @Override
    public List<Pedido> buscarTodosEmPreparacao(){
        List<Pedido> pedidosRecebidos = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.EM_PREPARACAO);
        return pedidosRecebidos;
    }
    @Override
    public List<Pedido> buscarTodosPronto(){
        List<Pedido> pedidosRecebidos = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.PRONTO);
        return pedidosRecebidos;
    }
    @Override
    public List<Pedido> buscarTodosFinalizado(){
        List<Pedido> pedidosRecebidos = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.FINALIZADO);
        return pedidosRecebidos;
    }
}
