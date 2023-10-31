package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.entity.integration.ComandaPedido;
import br.fiap.projeto.contexto.pedido.usecase.PedidoComandaIntegrationUseCase;
import br.fiap.projeto.contexto.pedido.usecase.enums.MensagemErro;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoComandaIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoWorkFlowUseCase;

public class PedidoComandaServiceTest {

    private PedidoComandaIntegrationUseCase pedidoComandaIntegrationUseCase;
    private IPedidoComandaIntegrationAdapterGateway pedidoComandaIntegrationAdapterGateway;
    private IPedidoWorkFlowUseCase pedidoWorkFlowUseCase;

    @BeforeEach
    public void setUp() {
        pedidoComandaIntegrationAdapterGateway = mock(IPedidoComandaIntegrationAdapterGateway.class);
        pedidoWorkFlowUseCase = mock(IPedidoWorkFlowUseCase.class);
        pedidoComandaIntegrationUseCase = new PedidoComandaIntegrationUseCase(pedidoComandaIntegrationAdapterGateway,
                pedidoWorkFlowUseCase);
    }

    @Test
    public void testCriaComandaComPedidoValido() throws Exception {
        UUID codigoPedido = UUID.randomUUID();
        Pedido pedido = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                LocalDateTime.now());

        // Configure o comportamento simulado do pedidoWorkFlowUseCase
        when(pedidoWorkFlowUseCase.preparar(codigoPedido)).thenReturn(pedido);

        // Configure o comportamento simulado do pedidoComandaIntegrationAdapterGateway
        ComandaPedido comandaPedido = new ComandaPedido(UUID.randomUUID(), pedido.getCodigo());
        when(pedidoComandaIntegrationAdapterGateway.criaComanda(codigoPedido)).thenReturn(comandaPedido);

        // Chame o método que você deseja testar
        Pedido resultado = pedidoComandaIntegrationUseCase.criaComanda(codigoPedido);

        // Verifique o resultado
        assertNotNull(resultado);
        assertEquals(pedido, resultado);
    }

    @Test
    public void testCriaComandaComPedidoNaoValido() throws Exception {
        UUID codigoPedido = UUID.randomUUID();
        Pedido pedido = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.FINALIZADO, 20d,
                LocalDateTime.now());

        // Configure o comportamento simulado do pedidoWorkFlowUseCase
        when(pedidoWorkFlowUseCase.preparar(codigoPedido)).thenReturn(pedido);

        // Tente chamar o método que você deseja testar e capture a exceção
        try {
            pedidoComandaIntegrationUseCase.criaComanda(codigoPedido);
            // Se não for lançada uma exceção, o teste falhou
            fail("Uma exceção era esperada.");
        } catch (Exception e) {
            // A exceção esperada foi lançada, o teste passou
            assertEquals(MensagemErro.STATUS_UPDATE_ERROR.getMessage(), e.getMessage());
        }
    }
}
