package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoQueryUseCase;

public class PedidoQueryRestAdapterControllerTest {

    private PedidoQueryRestAdapterController controller;
    private IPedidoQueryUseCase queryUseCase;
    private UUID codigoPedido;

    @BeforeEach
    public void setUp() {
        queryUseCase = mock(IPedidoQueryUseCase.class);
        controller = new PedidoQueryRestAdapterController(queryUseCase);
        codigoPedido = UUID.randomUUID();
    }

    @Test
    public void testBuscaPedido() {
        // Configurar o comportamento do mock para o método buscaPedido
        Pedido pedido = new Pedido("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
        when(queryUseCase.buscaPedido(codigoPedido)).thenReturn(pedido);

        PedidoDTO pedidoDTO = controller.buscaPedido(codigoPedido);

        // Verifique se o método no mock foi chamado com os parâmetros corretos
        verify(queryUseCase, times(1)).buscaPedido(codigoPedido);
        assertNotNull(pedidoDTO);
        assertEquals(pedido.getCliente(), pedidoDTO.getCliente());
    }

    @Test
    public void testBuscarTodosRecebido() {
        // Configurar o comportamento do mock para o método buscarTodosRecebido
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("5fc03087-d265-11e7-b8c6-83e29cd24f4c"),
                new Pedido("4fc03056-d265-11e7-b8c6-83e29cd24f4c"));

        when(queryUseCase.buscarTodosRecebido()).thenReturn(pedidos);

        List<PedidoDTO> pedidoDTOs = controller.buscarTodosRecebido();

        // Verifique se o método no mock foi chamado
        verify(queryUseCase, times(1)).buscarTodosRecebido();
        assertNotNull(pedidoDTOs);
        assertEquals(pedidoDTOs.size(), pedidos.size());

    }

}
