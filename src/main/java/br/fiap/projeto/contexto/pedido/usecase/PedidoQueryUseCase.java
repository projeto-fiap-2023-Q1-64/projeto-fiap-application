package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoQueryUseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PedidoQueryUseCase extends AbstractPedidoUseCase implements IPedidoQueryUseCase {
    public PedidoQueryUseCase(IPedidoRepositoryAdapterGateway IPedidoRepositoryAdapterGateway) {
        super(IPedidoRepositoryAdapterGateway);
    }

    @Override
    public Pedido buscaPedido(UUID codigoPedido) {
        return this.buscar(codigoPedido);
    }

    @Override
    public List<Pedido> buscaTodos() {
        return IPedidoRepositoryAdapterGateway.buscaTodos();
    }
    @Override
    public List<Pedido> buscarTodosRecebido(){
        return IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.RECEBIDO);
    }
    @Override
    public List<Pedido> buscarTodosPagos(){
        return IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.PAGO);
    }
    @Override
    public List<Pedido> buscarTodosEmPreparacao(){
        return IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.EM_PREPARACAO);
    }
    @Override
    public List<Pedido> buscarTodosPronto(){
        return IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.PRONTO);
    }
    @Override
    public List<Pedido> buscarTodosFinalizado(){
        return IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.FINALIZADO);
    }

    @Override
    public List<Pedido> buscarTodosCancelado() {
        return IPedidoRepositoryAdapterGateway.buscaPedidosPorStatus(StatusPedido.CANCELADO);
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
