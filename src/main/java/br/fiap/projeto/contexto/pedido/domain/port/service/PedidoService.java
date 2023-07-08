package br.fiap.projeto.contexto.pedido.domain.port.service;

import br.fiap.projeto.contexto.pedido.application.rest.response.ItemPedidoDTO;
import br.fiap.projeto.contexto.pedido.application.rest.request.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.application.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.application.rest.request.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.exception.InvalidOperacaoProdutoException;
import br.fiap.projeto.contexto.pedido.domain.exception.ItemNotFoundException;

import java.util.List;
import java.util.UUID;

public interface PedidoService {
    PedidoDTO criaPedido(PedidoCriarDTO pedidoDTO);
    PedidoDTO receber(UUID codigo) throws Exception;
    PedidoDTO pagar(UUID codigo) throws Exception;
    PedidoDTO preparar(UUID codigo) throws Exception;
    PedidoDTO buscaPedido(UUID codigo);
    List<PedidoDTO> buscaTodos();
    List<PedidoDTO> buscarTodosRecebido();

    List<PedidoDTO> buscarTodosPagos();

    List<PedidoDTO> buscarTodosEmPreparacao();
    List<PedidoDTO> buscarTodosPronto();
    List<PedidoDTO> buscarTodosFinalizado();
    void removePedido(UUID codigo);
    PedidoDTO prontificar(UUID codigo) throws Exception;
    PedidoDTO finalizar(UUID codigo) throws Exception;
    PedidoDTO adicionarProduto(UUID codigoPedido, ProdutoPedidoDTO produtoDTO) throws InvalidOperacaoProdutoException;
    PedidoDTO aumentarQuantidade(UUID codigoPedido, UUID codigoProduto) throws ItemNotFoundException, InvalidOperacaoProdutoException;
    PedidoDTO reduzirQuantidade(UUID codigoPedido, UUID codigoProduto) throws ItemNotFoundException, InvalidOperacaoProdutoException;
    void removerProduto(UUID codigoPedido, UUID codigoProduto) throws InvalidOperacaoProdutoException;
    Integer calcularTempoTotalPreparo(UUID codigo);
    List<ItemPedidoDTO> listarItens(UUID codigo);
}
