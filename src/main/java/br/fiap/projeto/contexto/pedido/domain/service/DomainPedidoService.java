package br.fiap.projeto.contexto.pedido.domain.service;

import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.domain.dto.ItemPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.port.repository.PedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DomainPedidoService implements PedidoService {
    private final PedidoRepositoryPort pedidoRepositoryPort;
    public DomainPedidoService(PedidoRepositoryPort pedidoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }
    @Override
    public PedidoDTO criaPedido(PedidoDTO pedidoDTO) {
        return pedidoRepositoryPort.criaPedido(new Pedido(pedidoDTO)).toPedidoDTO();
    }
    @Override
    public PedidoDTO buscaPedido(UUID codigo) {
        return pedidoRepositoryPort.buscaPedido(codigo).toPedidoDTO();
    }
    @Override
    public List<PedidoDTO> buscaTodos() {
        List<Pedido> pedidoLista = pedidoRepositoryPort.buscaTodos();
        return pedidoLista.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    @Override
    public PedidoDTO atualizaPedido(UUID codigo, PedidoDTO pedidoDTO) {
        return pedidoRepositoryPort.atualizaPedido(codigo, new Pedido(pedidoDTO)).toPedidoDTO();
    }
    @Override
    public void removePedido(UUID codigo) {
        pedidoRepositoryPort.removePedido(codigo);
    }
    @Override
    public Double calcularValorTotal() {
        return null;
    }
    @Override
    public void aumentarQuantidade(ProdutoPedidoDTO produtoDTO) {

    }
    @Override
    public void reduzirQuantidade(ProdutoPedidoDTO produtoDTO) {

    }
    @Override
    public Integer calcularTempoTotalPreparo() {
        return null;
    }
    @Override
    public void adicionarProduto(ProdutoPedidoDTO produtoDTO) {

    }
    @Override
    public void removerProduto(ProdutoPedidoDTO produtoDTO) {

    }
    @Override
    public List<ItemPedidoDTO> listarItens() {
        return null;
    }
}
