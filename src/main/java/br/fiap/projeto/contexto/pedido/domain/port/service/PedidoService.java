package br.fiap.projeto.contexto.pedido.domain.port.service;

import br.fiap.projeto.contexto.pedido.domain.dto.ItemPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.exception.InvalidOperacaoProdutoException;
import br.fiap.projeto.contexto.pedido.domain.exception.ItemNotFoundException;

import java.util.List;
import java.util.UUID;

public interface PedidoService {
    PedidoDTO criaPedido(PedidoCriarDTO pedidoDTO);

    //-------------------------------------------------------------------------//
    //                       MANIPULAÇÃO DE STATUS
    //-------------------------------------------------------------------------//
    PedidoDTO receber(UUID codigo) throws Exception;

    PedidoDTO aprovar(UUID codigo) throws Exception;
    PedidoDTO buscaPedido(UUID codigo);
    List<PedidoDTO> buscaTodos();
    List<PedidoDTO> buscarTodosRecebido();
    void removePedido(UUID codigo);
    PedidoDTO prontificar(UUID codigo) throws Exception;
    PedidoDTO finalizar(UUID codigo) throws Exception;
    PedidoDTO aumentarQuantidade(UUID codigo, ProdutoPedidoDTO produtoDTO) throws ItemNotFoundException, InvalidOperacaoProdutoException;
    PedidoDTO reduzirQuantidade(UUID codigo, ProdutoPedidoDTO produtoDTO) throws ItemNotFoundException, InvalidOperacaoProdutoException;
    Integer calcularTempoTotalPreparo(UUID codigo);
    PedidoDTO adicionarProduto(UUID codigo, ProdutoPedidoDTO produtoDTO) throws InvalidOperacaoProdutoException;
    void removerProduto(UUID codigo, UUID produtoCodigo) throws InvalidOperacaoProdutoException;
    List<ItemPedidoDTO> listarItens(UUID codigo);
}
