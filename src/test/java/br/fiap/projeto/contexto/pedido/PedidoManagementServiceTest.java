package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.pedido.entity.integration.ProdutoPedido;
import br.fiap.projeto.contexto.pedido.usecase.PedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.usecase.enums.MensagemErro;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.ItemNotFoundException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoClienteIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoProdutoIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoRepositoryAdapterGateway;

public class PedidoManagementServiceTest {

    private PedidoManagementUseCase pedidoManagementUseCase;
    private IPedidoRepositoryAdapterGateway pedidoRepositoryAdapterGateway;
    private IPedidoProdutoIntegrationAdapterGateway pedidoProdutoIntegrationAdapterGateway;
    private IPedidoClienteIntegrationAdapterGateway pedidoClienteIntegrationAdapterGateway;
    private Pedido pedido;
    private ProdutoPedido produtoPedido;
    private UUID codigoPedido;
    private UUID codigoProduto;

    @BeforeEach
    public void setUp() throws InvalidStatusException, NoItensException {
        pedidoRepositoryAdapterGateway = mock(IPedidoRepositoryAdapterGateway.class);
        pedidoProdutoIntegrationAdapterGateway = mock(IPedidoProdutoIntegrationAdapterGateway.class);
        pedidoClienteIntegrationAdapterGateway = mock(IPedidoClienteIntegrationAdapterGateway.class);

        pedidoManagementUseCase = new PedidoManagementUseCase(
                pedidoRepositoryAdapterGateway,
                pedidoProdutoIntegrationAdapterGateway,
                pedidoClienteIntegrationAdapterGateway);

        pedido = new Pedido("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
        codigoPedido = UUID.randomUUID();
        codigoProduto = UUID.randomUUID();
        produtoPedido = new ProdutoPedido(codigoProduto, "Produto Teste", "Descrição do Produto", 10.0,
                CategoriaProduto.LANCHE,
                "Imagem", 15);

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
}
