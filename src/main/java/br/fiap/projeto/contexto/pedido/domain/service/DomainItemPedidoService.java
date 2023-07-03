package br.fiap.projeto.contexto.pedido.domain.service;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.domain.dto.ItemPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.port.repository.ItemPedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.domain.port.service.ItemPedidoService;

import java.util.List;
import java.util.stream.Collectors;

public class DomainItemPedidoService implements ItemPedidoService {
    private final ItemPedidoRepositoryPort itemPedidoRepositoryPort;
    public DomainItemPedidoService(ItemPedidoRepositoryPort itemPedidoRepositoryPort) {
        this.itemPedidoRepositoryPort = itemPedidoRepositoryPort;
    }
    @Override
    public ItemPedidoDTO criaItemPedido(ItemPedidoDTO itemPedidoDTO) {
        return itemPedidoRepositoryPort.criaItemPedido(new ItemPedido(itemPedidoDTO)).toItemPedidoDTO();
    }
    @Override
    public ItemPedidoDTO buscaItemPedido(ItemPedidoCodigo codigo) {
        return itemPedidoRepositoryPort.buscaItemPedido(codigo).toItemPedidoDTO();
    }
    @Override
    public List<ItemPedidoDTO> buscaTodos() {
        List<ItemPedido> itemPedidoLista = itemPedidoRepositoryPort.buscaTodos();
        return itemPedidoLista.stream().map(ItemPedido::toItemPedidoDTO).collect(Collectors.toList());
    }
    @Override
    public ItemPedidoDTO atualizaItemPedido(ItemPedidoDTO itemPedidoDTO) {
        return itemPedidoRepositoryPort.atualizaItemPedido(new ItemPedido(itemPedidoDTO)).toItemPedidoDTO();
    }
    @Override
    public void removeItemPedido(ItemPedidoCodigo codigo) {
        itemPedidoRepositoryPort.removeItemPedido(codigo);
    }
    @Override
    public Double calcularValorTotal(ItemPedidoCodigo codigo) {
        ItemPedidoDTO itemPedido = this.buscaItemPedido(codigo);
        // TODO Ajustar logica para recuperar info do pedido
        //return itemPedido.getProdutoCodigo * itemPedido.getQuantidade();
        return null;
    }
    @Override
    public Integer calcularTempoTotalPreparo(ItemPedidoCodigo codigo) {
        ItemPedidoDTO itemPedido = this.buscaItemPedido(codigo);
        // TODO Ajustar logica para recuperar info do pedido
        //return itemPedido.getProdutoCodigo * itemPedido.getQuantidade();
        return null;
    }
}