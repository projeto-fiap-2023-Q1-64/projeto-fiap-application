package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.entity.integration.ComandaPedido;
import br.fiap.projeto.contexto.pedido.entity.integration.ProdutoPedido;
import br.fiap.projeto.contexto.pedido.usecase.PedidoComandaIntegrationUseCase;
import br.fiap.projeto.contexto.pedido.usecase.PedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.usecase.PedidoStatusDataComparator;
import br.fiap.projeto.contexto.pedido.usecase.enums.MensagemErro;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.ItemNotFoundException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoClienteIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoComandaIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoProdutoIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoWorkFlowUseCase;

public class PedidoServiceTest {

    @InjectMocks
    private PedidoManagementUseCase pedidoManagementUseCase;
    @InjectMocks
    private PedidoComandaIntegrationUseCase pedidoComandaIntegrationUseCase;
    @Mock
    private IPedidoRepositoryAdapterGateway pedidoRepositoryAdapterGateway;
    @Mock
    private IPedidoProdutoIntegrationAdapterGateway pedidoProdutoIntegrationAdapterGateway;
    @Mock
    private IPedidoClienteIntegrationAdapterGateway pedidoClienteIntegrationAdapterGateway;
    @Mock
    private IPedidoComandaIntegrationAdapterGateway pedidoComandaIntegrationAdapterGateway;
    @Mock
    private IPedidoWorkFlowUseCase pedidoWorkFlowUseCase;

    private Pedido pedido;
    private ProdutoPedido produtoPedido;
    private UUID codigoPedido;
    private UUID codigoProduto;
    private PedidoStatusDataComparator comparator;

    @BeforeEach
    public void setUp() throws InvalidStatusException, NoItensException {
        MockitoAnnotations.initMocks(this);

        pedido = new Pedido("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
        codigoPedido = UUID.randomUUID();
        codigoProduto = UUID.randomUUID();
        produtoPedido = new ProdutoPedido(codigoProduto, "Produto Teste", "Descrição do Produto", 10.0,
                CategoriaProduto.LANCHE,
                "Imagem", 15);

        comparator = new PedidoStatusDataComparator();

        when(pedidoRepositoryAdapterGateway.salvar(any(Pedido.class))).thenReturn(pedido);
        when(pedidoRepositoryAdapterGateway.buscaPedido(any(UUID.class))).thenReturn(Optional.of(pedido));
        when(pedidoProdutoIntegrationAdapterGateway.getProduto(codigoProduto)).thenReturn(produtoPedido);
    }

    @Test
    public void testCriaPedidoComClienteExistente() throws Exception {
        String codigoCliente = "5fc03087-d265-11e7-b8c6-83e29cd24f4c";
        Pedido pedido = new Pedido(codigoCliente);

        when(pedidoClienteIntegrationAdapterGateway.verificaClienteExiste(UUID.fromString(codigoCliente)))
                .thenReturn(true);
        when(pedidoRepositoryAdapterGateway.salvar(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoManagementUseCase.criaPedido(codigoCliente);

        assertNotNull(resultado);
        assertEquals(pedido, resultado);
    }

    @Test
    public void testCriaPedidoComClienteNaoExistente() throws Exception {
        String codigoCliente = "5fc03087-d265-11e7-b8c6-83e29cd24f4c";

        when(pedidoClienteIntegrationAdapterGateway.verificaClienteExiste(UUID.fromString(codigoCliente)))
                .thenReturn(false);

        try {
            pedidoManagementUseCase.criaPedido(codigoCliente);
            fail("Uma exceção era esperada.");
        } catch (ObjectNotFoundException e) {
            assertEquals(codigoCliente, e.getIdentifier());
            assertEquals("cliente", e.getEntityName());
        }
    }

    @Test
    public void testAdicionarProdutoProdutoExistente() throws Exception {
        when(pedidoProdutoIntegrationAdapterGateway.getProduto(codigoProduto)).thenReturn(produtoPedido);
        Pedido resultado = pedidoManagementUseCase.adicionarProduto(codigoPedido, codigoProduto);
        assertNotNull(resultado);
    }

    @Test
    public void testAdicionarProdutoProdutoNaoExistente() throws Exception {
        when(pedidoProdutoIntegrationAdapterGateway.getProduto(codigoProduto)).thenReturn(null);

        try {
            pedidoManagementUseCase.adicionarProduto(codigoPedido, codigoProduto);
            fail("Uma exceção era esperada.");
        } catch (ItemNotFoundException e) {
            assertEquals(MensagemErro.PRODUTO_NOT_FOUND.getMessage(), e.getMessage());
        }
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

    @Test
    public void testComparePedidosComMesmaPrioridade() throws InvalidStatusException, NoItensException {
        Pedido pedido1 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                LocalDateTime.now());
        Pedido pedido2 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                LocalDateTime.now());

        int result = comparator.compare(pedido1, pedido2);

        assertEquals(-1, result);
    }

    @Test
    public void testComparePedidosComDiferentePrioridade() throws InvalidStatusException, NoItensException {
        Pedido pedido1 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                LocalDateTime.now());
        Pedido pedido2 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.RECEBIDO, 20d,
                LocalDateTime.now());

        int result = comparator.compare(pedido1, pedido2);

        assertTrue(result < 0);
    }

    @Test
    public void testComparePedidosComDiferenteStatusEMesmaPrioridade()
            throws InvalidStatusException, NoItensException {
        Pedido pedido1 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                LocalDateTime.now());
        Pedido pedido2 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.RECEBIDO, 20d,
                LocalDateTime.now());

        int result = comparator.compare(pedido1, pedido2);

        assertTrue(result < 0);
    }

    @Test
    public void testComparePedidosComDiferenteStatusEMaiorPrioridade()
            throws InvalidStatusException, NoItensException {
        Pedido pedido1 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                LocalDateTime.now());
        Pedido pedido2 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.RECEBIDO, 20d,
                LocalDateTime.now());

        int result = comparator.compare(pedido1, pedido2);

        assertTrue(result < 0);
    }

    @Test
    public void testComparePedidosComDiferenteStatusEPrioridadeMaiorQueZero()
            throws InvalidStatusException, NoItensException {
        Pedido pedido1 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.PRONTO, 20d,
                LocalDateTime.now());
        Pedido pedido2 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.RECEBIDO, 20d,
                LocalDateTime.now());

        int result = comparator.compare(pedido1, pedido2);

        assertTrue(result < 0);
    }

}
