package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoPagamentoIntegrationRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pedido.entity.integration.PagamentoPedido;
import br.fiap.projeto.contexto.pedido.external.integration.port.Pagamento;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoPagamentoIntegrationUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoQueryUseCase;

public class PedidoControllerTest {

    @InjectMocks
    private PedidoManagementRestAdapterController controller;
    @InjectMocks
    private PedidoQueryRestAdapterController controllerQuery;
    @InjectMocks
    private PedidoPagamentoIntegrationRestAdapterController controllerPagamento;
    @Mock
    private IPedidoManagementUseCase pedidoManagementUseCase;
    @Mock
    private IPedidoQueryUseCase queryUseCase;
    @Mock
    private IPedidoPagamentoIntegrationUseCase pedidoPagamentoIntegrationUseCase;

    private String codigoCliente;
    private UUID codigoPedido;
    private UUID codigoProduto;
    private PagamentoPedido pagamentoPedido;
    private Pagamento pagamento;
    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        codigoCliente = "cliente123";
        codigoPedido = UUID.randomUUID();
        codigoProduto = UUID.randomUUID();

        pagamentoPedido = new PagamentoPedido(codigoPedido.toString(), new Date(1234454),
                StatusPagamento.IN_PROCESS);
        pagamento = new Pagamento();
        pedido = new Pedido("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
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

    @Test
    public void testBuscaPedido() {
        // Configurar o comportamento do mock para o método buscaPedido
        Pedido pedido = new Pedido("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
        when(queryUseCase.buscaPedido(codigoPedido)).thenReturn(pedido);

        PedidoDTO pedidoDTO = controllerQuery.buscaPedido(codigoPedido);

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

        List<PedidoDTO> pedidoDTOs = controllerQuery.buscarTodosRecebido();

        // Verifique se o método no mock foi chamado
        verify(queryUseCase, times(1)).buscarTodosRecebido();
        assertNotNull(pedidoDTOs);
        assertEquals(pedidoDTOs.size(), pedidos.size());

    }

    @Test
    public void testAtualizarPagamentoPedido() throws Exception {
        // Configurar o comportamento do mock para o método atualizarPagamentoPedido
        when(pedidoPagamentoIntegrationUseCase.atualizarPagamentoPedido(codigoPedido)).thenReturn(pedido);

        PedidoDTO resultado = controllerPagamento.atualizarPagamentoPedido(codigoPedido);

        assertNotNull(resultado);
        
    }

    @Test
    public void testRecebeRetornoPagamento() throws Exception {

        doThrow(new Exception("Mensagem de erro")).when(pedidoPagamentoIntegrationUseCase)
                .recebeRetornoPagamento(any(PagamentoPedido.class));

        try {
            controllerPagamento.recebeRetornoPagamento(pagamento);
            fail("Deveria ter lançado uma exceção");
        } catch (Exception e) {
            // Verifique a mensagem de erro ou qualquer outra verificação necessária
            assertTrue(e.getMessage().contains("Mensagem de erro"));
        }
    }
}
