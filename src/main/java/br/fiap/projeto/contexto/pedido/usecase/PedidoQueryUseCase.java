package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoQueryUseCase;

import java.util.ArrayList;
import java.util.Collections;
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
        // Define os status que queremos filtrar
        List<StatusPedido> statuses = new ArrayList<>();
        statuses.add(StatusPedido.RECEBIDO);
        statuses.add(StatusPedido.PAGO);
        statuses.add(StatusPedido.EM_PREPARACAO);
        statuses.add(StatusPedido.PRONTO);

        // Recupera os pedidos destes estados
        List<Pedido> pedidoLista = IPedidoRepositoryAdapterGateway.buscaPedidorPorStatuses(statuses);

        // Ordenamos de acordo com o status e data de criação do pedido
        Collections.sort(pedidoLista, new PedidoStatusDataComparator());

        return pedidoLista;
    }
}
