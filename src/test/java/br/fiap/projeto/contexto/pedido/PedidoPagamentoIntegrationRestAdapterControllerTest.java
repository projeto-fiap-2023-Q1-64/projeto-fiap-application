package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoPagamentoIntegrationRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.integration.PagamentoPedido;
import br.fiap.projeto.contexto.pedido.external.integration.port.Pagamento;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoPagamentoIntegrationUseCase;

public class PedidoPagamentoIntegrationRestAdapterControllerTest {

    private PedidoPagamentoIntegrationRestAdapterController controller;
    private IPedidoPagamentoIntegrationUseCase pedidoPagamentoIntegrationUseCase;
    private UUID codigoPedido;
    private Pedido pedido;
    private Pagamento pagamento;
    private PagamentoPedido pagamentoPedido;

    @BeforeEach
    public void setUp() {
        pedidoPagamentoIntegrationUseCase = mock(IPedidoPagamentoIntegrationUseCase.class);
        controller = new PedidoPagamentoIntegrationRestAdapterController(pedidoPagamentoIntegrationUseCase);
        codigoPedido = UUID.randomUUID();
        pedido = new Pedido("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
        pagamento = new Pagamento();
        pagamentoPedido = new PagamentoPedido(codigoPedido.toString(), new Date(1234454),
                br.fiap.projeto.contexto.pedido.entity.enums.StatusPagamento.IN_PROCESS);
    }

    @Test
    public void testAtualizarPagamentoPedido() throws Exception {
        // Configurar o comportamento do mock para o método atualizarPagamentoPedido
        when(pedidoPagamentoIntegrationUseCase.atualizarPagamentoPedido(codigoPedido)).thenReturn(pedido);

        PedidoDTO resultado = controller.atualizarPagamentoPedido(codigoPedido);

        assertNotNull(resultado);
        
    }

    @Test
    public void testRecebeRetornoPagamento() throws Exception {

        doThrow(new Exception("Mensagem de erro")).when(pedidoPagamentoIntegrationUseCase)
                .recebeRetornoPagamento(any(PagamentoPedido.class));

        try {
            controller.recebeRetornoPagamento(pagamento);
            fail("Deveria ter lançado uma exceção");
        } catch (Exception e) {
            // Verifique a mensagem de erro ou qualquer outra verificação necessária
            assertTrue(e.getMessage().contains("Mensagem de erro"));
        }
    }

}
