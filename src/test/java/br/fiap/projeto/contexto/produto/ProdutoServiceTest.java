package br.fiap.projeto.contexto.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;

import br.fiap.projeto.contexto.produto.entity.Produto;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.usecase.GestaoProdutoUseCase;
import br.fiap.projeto.contexto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.produto.usecase.exception.ProdutoNaoEncontradoException;
import br.fiap.projeto.contexto.produto.usecase.port.IProdutoRepositoryAdapterGateway;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @InjectMocks
    private GestaoProdutoUseCase gestao;

    @Mock
    private IProdutoRepositoryAdapterGateway produtoRepositoryAdapterGateway;

    @Test
    public void criaProdutoComProdutoValido() throws EntradaInvalidaException {

        gestao = new GestaoProdutoUseCase(produtoRepositoryAdapterGateway);

        // Preparação de dados de teste
        Produto produto = new Produto("123", "Produto de Teste", "Descrição de Teste", 10.0, CategoriaProduto.LANCHE,
                "Imagem de Teste", 30);

        // Configuração de comportamento simulado para o produtoAdapterGateway

        Mockito.when(produtoRepositoryAdapterGateway.criaProduto(Mockito.any(Produto.class)))
                .thenReturn(produto);

        // Execução do método
        Produto resultado = gestao.criaProduto(produto);

        // Verificação dos resultados
        assertNotNull(resultado);
        assertEquals(produto, resultado);
    }

    @Test
    public void criaProdutoComProdutoNulo() {

        gestao = new GestaoProdutoUseCase(produtoRepositoryAdapterGateway);
        // Preparação de dados de teste
        Produto produtoNulo = null;

        // Execução do método
        assertThrows(EntradaInvalidaException.class, () -> {
            gestao.criaProduto(produtoNulo);
        });
    }

    @Test
    public void atualizaProdutoComProdutoExistente()
            throws ProdutoNaoEncontradoException, EntradaInvalidaException {

        // gestao = new GestaoProdutoUseCase(produtoRepositoryAdapterGateway);

        // Preparação de dados de teste
        String codigoProduto = "12345";
        Produto produto = new Produto("12345", "Produto de Teste", "Descrição de Teste", 10.0, CategoriaProduto.LANCHE,
                "Imagem de Teste", 30);

        // Configuração do comportamento simulado do produtoAdapterGateway
        Mockito.when(produtoRepositoryAdapterGateway.buscaProduto(produto.getCodigo()))
                .thenReturn(Optional.of(produto));

        // Execução do método
        // assertEquals(codigoProduto, produto.getCodigo());
        gestao.atualizaProduto(codigoProduto, produto);

        // Verificação dos resultados
        Mockito.verify(produtoRepositoryAdapterGateway).atualizaProduto(Mockito.any(Produto.class));
    }

    @Test
    public void atualizaProdutoComProdutoNaoExistente() throws EntradaInvalidaException {

        // gestao = new GestaoProdutoUseCase(produtoRepositoryAdapterGateway);

        // Preparação de dados de teste
        String codigoProdutoNaoExistente = "97654";
        Produto produto = new Produto("12345", "Produto de Teste", "Descrição de Teste", 10.0, CategoriaProduto.LANCHE,
                "Imagem de Teste", 30);

        // Configuração do comportamento simulado do produtoAdapterGateway
        Mockito.when(produtoRepositoryAdapterGateway.buscaProduto(codigoProdutoNaoExistente))
                .thenReturn(Optional.empty());

        // Execução do método e verificação da exceção
        try {
            gestao.atualizaProduto(codigoProdutoNaoExistente, produto);
            throw new AssertionFailedError("Deveria ter lançado ProdutoNaoEncontradoException");
        } catch (ProdutoNaoEncontradoException e) {
            // ProdutoNaoEncontradoException esperada
        }
    }

    @Test
    public void removeProdutoComProdutoExistente() throws ProdutoNaoEncontradoException, EntradaInvalidaException {
        // Preparação de dados de teste
        String codigoProduto = "12345";
        Produto produtoExistente = new Produto("12345", "Produto de Teste", "Descrição de Teste", 10.0,
                CategoriaProduto.LANCHE,
                "Imagem de Teste", 30);

        // Configuração do comportamento simulado do produtoAdapterGateway
        Mockito.when(produtoRepositoryAdapterGateway.buscaProduto(produtoExistente.getCodigo()))
                .thenReturn(Optional.of(produtoExistente));

        // Execução do método
        gestao.removeProduto(codigoProduto);

        // Verificação dos resultados
        Mockito.verify(produtoRepositoryAdapterGateway).removeProduto(codigoProduto);
    }

    @Test
    public void removeProdutoComProdutoNaoExistente() {
        // Preparação de dados de teste
        String codigoProdutoNaoExistente = "98765";

        // Configuração do comportamento simulado do produtoAdapterGateway
        Mockito.when(produtoRepositoryAdapterGateway.buscaProduto(codigoProdutoNaoExistente))
                .thenReturn(Optional.empty());

        // Execução do método e verificação da exceção
        try {
            gestao.removeProduto(codigoProdutoNaoExistente);
            throw new AssertionFailedError("Deveria ter lançado ProdutoNaoEncontradoException");
        } catch (ProdutoNaoEncontradoException e) {
            // ProdutoNaoEncontradoException esperada
        }
    }

    @Test
    public void buscaProdutoComProdutoExistente() throws ProdutoNaoEncontradoException, EntradaInvalidaException {
        // Preparação de dados de teste
        String codigoProduto = "12345";
        Produto produtoExistente = new Produto("12345", "Produto de Teste", "Descrição de Teste", 10.0,
                CategoriaProduto.LANCHE,
                "Imagem de Teste", 30);

        // Configuração do comportamento simulado do produtoAdapterGateway
        Mockito.when(produtoRepositoryAdapterGateway.buscaProduto(produtoExistente.getCodigo()))
                .thenReturn(Optional.of(produtoExistente));

        // Execução do método
        Produto resultado = gestao.buscaProduto(codigoProduto);

        // Verificação dos resultados
        Mockito.verify(produtoRepositoryAdapterGateway).buscaProduto(codigoProduto);
        assertEquals(produtoExistente, resultado);
    }

    @Test
    public void buscaProdutoComProdutoNaoExistente() {
        // Preparação de dados de teste
        String codigoProdutoNaoExistente = "98765";

        // Configuração do comportamento simulado do produtoAdapterGateway
        Mockito.when(produtoRepositoryAdapterGateway.buscaProduto(codigoProdutoNaoExistente))
                .thenReturn(Optional.empty());

        // Execução do método e verificação da exceção
        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            gestao.buscaProduto(codigoProdutoNaoExistente);
        });
    }

    @Test
    public void buscaProdutosPorCategoria() throws EntradaInvalidaException {
        // Preparação de dados de teste
        CategoriaProduto categoria = CategoriaProduto.LANCHE;
        List<Produto> produtosSimulados = Arrays.asList(
                new Produto("1", "Produto 1", "Descrição 1", 10.0, CategoriaProduto.LANCHE, "Imagem 1", 20),
                new Produto("2", "Produto 2", "Descrição 2", 15.0, CategoriaProduto.LANCHE, "Imagem 2", 25));

        // Configuração do comportamento simulado do produtoAdapterGateway
        Mockito.when(produtoRepositoryAdapterGateway.buscaProdutosPorCategoria(categoria))
                .thenReturn(produtosSimulados);

        // Execução do método
        List<Produto> resultados = gestao.buscaProdutosPorCategoria(categoria);

        // Verificação dos resultados
        Mockito.verify(produtoRepositoryAdapterGateway).buscaProdutosPorCategoria(categoria);
        assertEquals(produtosSimulados, resultados);
    }

    @Test
    public void buscaTodosProdutos() throws EntradaInvalidaException {
        // Preparação de dados de teste
        CategoriaProduto categoria = CategoriaProduto.LANCHE;
        List<Produto> produtosSimulados = Arrays.asList(
                new Produto("1", "Produto 1", "Descrição 1", 10.0, CategoriaProduto.LANCHE, "Imagem 1", 20),
                new Produto("2", "Produto 2", "Descrição 2", 15.0, CategoriaProduto.BEBIDA, "Imagem 2", 25));

        // Configuração do comportamento simulado do produtoAdapterGateway
        Mockito.when(produtoRepositoryAdapterGateway.buscaProdutosPorCategoria(categoria))
                .thenReturn(produtosSimulados);

        // Execução do método
        List<Produto> resultados = gestao.buscaProdutosPorCategoria(categoria);

        // Verificação dos resultados
        Mockito.verify(produtoRepositoryAdapterGateway).buscaProdutosPorCategoria(categoria);
        assertEquals(produtosSimulados, resultados);
    }
}
