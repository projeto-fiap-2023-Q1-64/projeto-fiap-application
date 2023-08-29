package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoQueryUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoRepositoryAdapterGateway;

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
        List<Pedido> pedidoLista = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.RECEBIDO);
        return pedidoLista;
    }
    @Override
    public List<Pedido> buscarTodosPagos(){
        List<Pedido> pedidoLista = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.PAGO);
        return pedidoLista;
    }
    @Override
    public List<Pedido> buscarTodosEmPreparacao(){
        List<Pedido> pedidoLista = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.EM_PREPARACAO);
        return pedidoLista;
    }
    @Override
    public List<Pedido> buscarTodosPronto(){
        List<Pedido> pedidoLista = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.PRONTO);
        return pedidoLista;
    }
    @Override
    public List<Pedido> buscarTodosFinalizado(){
        List<Pedido> pedidoLista = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.FINALIZADO);
        return pedidoLista;
    }

    @Override
    public List<Pedido> buscarTodosPorStatusEDataCriacao() {
        List<Pedido> pedidoLista = IPedidoRepositoryAdapterGateway.buscaPedidosPorStatusData();
        return pedidoLista;
    }
}
