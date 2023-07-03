package br.fiap.projeto.contexto.pedido.domain.port.service;

import br.fiap.projeto.contexto.pedido.domain.dto.ItemPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.ProdutoPedidoDTO;
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
    void removePedido(UUID codigo);

    PedidoDTO prontificar(UUID codigo) throws Exception;

    PedidoDTO finalizar(UUID codigo) throws Exception;

    Double calcularValorTotal(UUID codigo);
    PedidoDTO aumentarQuantidade(UUID codigo, ProdutoPedidoDTO produtoDTO) throws ItemNotFoundException;
    PedidoDTO reduzirQuantidade(UUID codigo, ProdutoPedidoDTO produtoDTO) throws ItemNotFoundException;
    Integer calcularTempoTotalPreparo(UUID codigo);
    PedidoDTO adicionarProduto(UUID codigo, ProdutoPedidoDTO produtoDTO);
    void removerProduto(UUID codigo, UUID produtoCodigo);
    List<ItemPedidoDTO> listarItens(UUID codigo);
}
