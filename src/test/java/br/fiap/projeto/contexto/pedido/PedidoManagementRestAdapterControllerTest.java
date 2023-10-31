package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoManagementUseCase;

public class PedidoManagementRestAdapterControllerTest {

    private PedidoManagementRestAdapterController controller;
    private IPedidoManagementUseCase pedidoManagementUseCase;
    private String codigoCliente;
    private UUID codigoPedido;
    private UUID codigoProduto;

    @BeforeEach
    public void setUp() {
        pedidoManagementUseCase = mock(IPedidoManagementUseCase.class);
        controller = new PedidoManagementRestAdapterController(pedidoManagementUseCase);
        codigoCliente = "cliente123";
        codigoPedido = UUID.randomUUID();
        codigoProduto = UUID.randomUUID();
    }

    @Test
    public void testCriaPedido() throws Exception {
        // Configurar o comportamento do mock para o método criaPedido
        Pedido pedido = new Pedido("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
        when(pedidoManagementUseCase.criaPedido(codigoCliente)).thenReturn(pedido);

        PedidoDTO pedidoDTO = controller.criaPedido(codigoCliente);

        verify(pedidoManagementUseCase, times(1)).criaPedido(codigoCliente);
        assertNotNull(pedidoDTO);
        assertEquals(pedido.getCliente(), pedidoDTO.getCliente());

    }

    @Test
    public void testAdicionarProduto() throws Exception {
        // Configurar o comportamento do mock para o método adicionarProduto
        Pedido pedido = new Pedido("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
        when(pedidoManagementUseCase.adicionarProduto(codigoPedido, codigoProduto)).thenReturn(pedido);

        PedidoDTO pedidoDTO = controller.adicionarProduto(codigoPedido, codigoProduto);

        // Verifique se o método no mock foi chamado com os parâmetros corretos
        verify(pedidoManagementUseCase, times(1)).adicionarProduto(codigoPedido, codigoProduto);
        assertNotNull(pedidoDTO);

    }

    @Test
    public void testRemoverProduto() throws Exception {
        // Configurar o comportamento do mock para o método removerProduto
        doNothing().when(pedidoManagementUseCase).removerProduto(codigoPedido, codigoProduto);

        controller.removerProduto(codigoPedido, codigoProduto);

        // Verifique se o método no mock foi chamado com os parâmetros corretos
        verify(pedidoManagementUseCase, times(1)).removerProduto(codigoPedido, codigoProduto);
    }

    @Test
    public void testAumentarQuantidade() throws Exception {
        // Configurar o comportamento do mock para o método aumentarQuantidade
        Pedido pedido = new Pedido(/* preencha com os dados necessários */);
        when(pedidoManagementUseCase.aumentarQuantidade(codigoPedido, codigoProduto)).thenReturn(pedido);

        PedidoDTO pedidoDTO = controller.aumentarQuantidade(codigoPedido, codigoProduto);

        // Verifique se o método no mock foi chamado com os parâmetros corretos
        verify(pedidoManagementUseCase, times(1)).aumentarQuantidade(codigoPedido, codigoProduto);
        assertNotNull(pedidoDTO);

    }

    @Test
    public void testReduzirQuantidade() throws Exception {
        // Configurar o comportamento do mock para o método reduzirQuantidade
        Pedido pedido = new Pedido(/* preencha com os dados necessários */);
        when(pedidoManagementUseCase.reduzirQuantidade(codigoPedido, codigoProduto)).thenReturn(pedido);

        PedidoDTO pedidoDTO = controller.reduzirQuantidade(codigoPedido, codigoProduto);

        // Verifique se o método no mock foi chamado com os parâmetros corretos
        verify(pedidoManagementUseCase, times(1)).reduzirQuantidade(codigoPedido, codigoProduto);
        assertNotNull(pedidoDTO);

    }

}
