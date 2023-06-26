package br.fiap.projeto.contexto.pedido.domain.service;

import br.fiap.projeto.contexto.pedido.domain.ProdutoPedido;
import br.fiap.projeto.contexto.pedido.domain.dto.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.port.repository.ProdutoPedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.domain.port.service.ProdutoPedidoService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DomainProdutoPedidoService implements ProdutoPedidoService {
    private final ProdutoPedidoRepositoryPort produtoPedidoRepositoryPort;
    public DomainProdutoPedidoService(ProdutoPedidoRepositoryPort produtoPedidoRepositoryPort) {
        this.produtoPedidoRepositoryPort = produtoPedidoRepositoryPort;
    }
    @Override
    public ProdutoPedidoDTO criaProdutoPedido(ProdutoPedidoDTO produtoPedidoDTO) {
        return produtoPedidoRepositoryPort.criaProdutoPedido(new ProdutoPedido(produtoPedidoDTO)).toProdutoPedidoDTO();
    }
    @Override
    public ProdutoPedidoDTO buscaProdutoPedido(UUID codigo) {
        return produtoPedidoRepositoryPort.buscaProdutoPedido(codigo).toProdutoPedidoDTO();
    }
    @Override
    public List<ProdutoPedidoDTO> buscaTodos() {
        List<ProdutoPedido> produtoPedidoLista = produtoPedidoRepositoryPort.buscaTodos();
        return produtoPedidoLista.stream().map(ProdutoPedido::toProdutoPedidoDTO).collect(Collectors.toList());
    }
    @Override
    public ProdutoPedidoDTO atualizaProdutoPedido(UUID codigo, ProdutoPedidoDTO produtoPedidoDTO) {
        return produtoPedidoRepositoryPort.atualizaProdutoPedido(codigo, new ProdutoPedido(produtoPedidoDTO)).toProdutoPedidoDTO();
    }
    @Override
    public void removeProdutoPedido(UUID codigo) {
        produtoPedidoRepositoryPort.removeProdutoPedido(codigo);
    }
}