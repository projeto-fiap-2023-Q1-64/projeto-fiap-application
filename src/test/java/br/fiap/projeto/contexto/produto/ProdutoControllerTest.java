package br.fiap.projeto.contexto.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.fiap.projeto.contexto.produto.adapter.controller.ProdutoRestAdapterController;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.request.ProdutoDTORequest;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.response.ProdutoDTOResponse;
import br.fiap.projeto.contexto.produto.entity.Produto;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.produto.usecase.exception.ProdutoNaoEncontradoException;
import br.fiap.projeto.contexto.produto.usecase.port.IGestaoProdutoUseCase;

public class ProdutoControllerTest {

    @InjectMocks
    private ProdutoRestAdapterController controller;

    @Mock
    private IGestaoProdutoUseCase gestaoProdutoUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void criaProduto() throws EntradaInvalidaException {

        controller = new ProdutoRestAdapterController(gestaoProdutoUseCase);

        // Preparação de dados de teste
        ProdutoDTORequest produtoDTORequest = new ProdutoDTORequest("Produto de Teste", "Descrição de Teste", 10.0,
                "LANCHE", "Imagem de Teste", 30);
        // Produto produtoSimulado = new Produto("Produto de Teste", "Descrição de
        // Teste", 10.0, CategoriaProduto.LANCHE,
        // "Imagem de Teste", 30);
        Produto produtoSimulado = produtoDTORequest.toProduto();

        // Configuração do comportamento simulado do produtoUseCase
        Mockito.when(gestaoProdutoUseCase.criaProduto(ArgumentMatchers.any(Produto.class))).thenReturn(produtoSimulado);

        // Execução do método
        ProdutoDTOResponse resultado = controller.criaProduto(produtoDTORequest);

        // Verificação dos resultados
        // Mockito.verify(gestaoProdutoUseCase).criaProduto(produtoDTORequest.toProduto());

        assertEquals(produtoSimulado.getNome(), resultado.getNome());
        assertEquals(produtoSimulado.getDescricao(), resultado.getDescricao());
        assertEquals(produtoSimulado.getPreco(), resultado.getPreco());
        // Verificar outras propriedades do DTO de resposta, se necessário
    }

    @Test
    public void testRemoveProduto() throws ProdutoNaoEncontradoException {
        // Preparação de dados de teste
        String codigoProduto = "12345";

        // Configuração do comportamento simulado do produtoUseCase
        Mockito.doNothing().when(gestaoProdutoUseCase).removeProduto(codigoProduto);

        // Execução do método
        controller.removeProduto(codigoProduto);

        // Verificação dos resultados
        Mockito.verify(gestaoProdutoUseCase).removeProduto(codigoProduto);
    }

    @Test
    public void removeProdutoComProdutoNaoEncontradoException() throws ProdutoNaoEncontradoException {
        // Preparação de dados de teste
        String codigoProduto = "12345";
        //

        // Configuração do comportamento simulado do produtoUseCase para lançar uma
        // exceção
        Mockito.doThrow(ProdutoNaoEncontradoException.class).when(gestaoProdutoUseCase).removeProduto(codigoProduto);

        // Execução do método e verificação da exceção
        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            controller.removeProduto(codigoProduto);
        });

    }

    @Test
    public void buscaProduto() throws ProdutoNaoEncontradoException, EntradaInvalidaException {
        // Preparação de dados de teste
        String codigoProduto = "12345";
        Produto produtoSimulado = new Produto("12345", "Produto de Teste", "Descrição de Teste", 10.0,
                CategoriaProduto.LANCHE, "Imagem de Teste", 30);

        // Configuração do comportamento simulado do produtoUseCase
        Mockito.when(gestaoProdutoUseCase.buscaProduto(codigoProduto)).thenReturn(produtoSimulado);

        // Execução do método
        ProdutoDTOResponse resultado = controller.buscaProduto(produtoSimulado.getCodigo());

        // Verificação dos resultados
        Mockito.verify(gestaoProdutoUseCase).buscaProduto(codigoProduto);
        assertNotNull(resultado);
        assertEquals(produtoSimulado.getNome(), resultado.getNome());
        assertEquals(produtoSimulado.getDescricao(), resultado.getDescricao());
        assertEquals(produtoSimulado.getPreco(), resultado.getPreco());

    }

}
