package br.fiap.projeto.contexto.comanda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.usecase.BuscaComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.BuscaRecebidoStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.CriarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.FinalizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.PrepararComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaDuplicadaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.IntegracaoPedidoException;
import br.fiap.projeto.contexto.comanda.usecase.exception.StatusNuloException;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorStatusComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.ICriarComandaRepositoryUseCase;

public class ComandaServiceTest {

    @InjectMocks
    private CriarComandaUseCase criarComandaUseCase;
    @InjectMocks
    private PrepararComandaUseCase prepararComandaUseCase;
    @InjectMocks
    private FinalizarComandaUseCase finalizarComandaUseCase;
    @InjectMocks
    private BuscaComandaUseCase buscaComandaUseCase;
    @InjectMocks
    private BuscaRecebidoStatusComandaUseCase buscaRecebidoStatusComandaUseCase;

    @Mock
    private ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase;
    @Mock
    private IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase;
    @Mock
    private IAtualizarComandaRepositoryUseCase atualizarComandaRepositoryUseCase;
    @Mock
    ComandaPedidoIntegration comandaPedidoIntegration;
    @Mock
    IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase;
    @Mock
    IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testCriarComandaOk() throws StatusNuloException, EntradaInvalidaException, ComandaDuplicadaException {
        // Gerar massa
        Comanda comanda = new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO);

        // Prepara o Mock
        Mockito.when(criarComandaRepositoryUseCase.criar(any(Comanda.class))).thenReturn(comanda);

        // Testa o método
        Comanda resultado = criarComandaUseCase.criarComanda(comanda.getCodigoPedido());

        // Validações
        assertNotNull(resultado);
        assertEquals(comanda.getCodigoComanda(), resultado.getCodigoComanda());
    }

    @Test
    public void testPreparaComanda() throws StatusNuloException, EntradaInvalidaException {
        // Gerar massa
        Comanda comanda = new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO);

        // Prepara o mock
        Mockito.when(atualizarComandaRepositoryUseCase.atualizar(any(Comanda.class))).thenReturn(comanda);
        Mockito.when(buscarPorComandaPorCodigoPedidoRepositoryUseCase.buscar(comanda.getCodigoPedido()))
                .thenReturn(Optional.of(comanda));

        // Testo o método
        Comanda resultado = prepararComandaUseCase.alterarStatus(comanda.getCodigoPedido());

        // Validações
        assertNotNull(resultado);
        assertEquals(comanda.getCodigoComanda(), resultado.getCodigoComanda());
        assertEquals(comanda.getStatus(), resultado.getStatus());
    }

    @Test
    public void testPreparaNaoComanda() throws StatusNuloException, EntradaInvalidaException {
        // Gerar massa
        Comanda comanda = new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO);

        // Prepara o mock
        Mockito.when(atualizarComandaRepositoryUseCase.atualizar(any(Comanda.class))).thenReturn(comanda);
        Mockito.when(buscarPorComandaPorCodigoPedidoRepositoryUseCase.buscar(comanda.getCodigoPedido()))
                .thenReturn(Optional.of(comanda));

        // Testo o método
        assertThrows(EntradaInvalidaException.class,
                () -> prepararComandaUseCase.alterarStatus(comanda.getCodigoPedido()),
                "Status da comanda inválido para esta operação! Precisa estar em RECEBIDO.");

    }

    @Test
    public void testFinalizaComanda() throws StatusNuloException, EntradaInvalidaException, IntegracaoPedidoException {
        // Gerar massa
        Comanda comanda = new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO);

        // Prepara o mock
        Mockito.when(buscarPorComandaPorCodigoPedidoRepositoryUseCase.buscar(comanda.getCodigoPedido()))
                .thenReturn(Optional.of(comanda));
        Mockito.when(atualizarComandaRepositoryUseCase.atualizar(any(Comanda.class))).thenReturn(comanda);

        Mockito.when(comandaPedidoIntegration.prontificar(comanda.getCodigoPedido().toString()))
                .thenReturn(ResponseEntity.ok("STATUS_ATUALIZADO"));

        // Testo o método
        Comanda resultado = finalizarComandaUseCase.alterarStatus(comanda.getCodigoPedido());

        // Validações
        assertNotNull(resultado);
        assertEquals(comanda.getCodigoComanda(), resultado.getCodigoComanda());
        assertEquals(comanda.getStatus(), resultado.getStatus());
    }

    @Test
    public void testFinalizaNaoComanda()
            throws StatusNuloException, EntradaInvalidaException, IntegracaoPedidoException {
        // Gerar massa
        Comanda comanda = new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO);

        // Prepara o mock
        Mockito.when(buscarPorComandaPorCodigoPedidoRepositoryUseCase.buscar(comanda.getCodigoPedido()))
                .thenReturn(Optional.of(comanda));
        Mockito.when(atualizarComandaRepositoryUseCase.atualizar(any(Comanda.class))).thenReturn(comanda);

        Mockito.when(comandaPedidoIntegration.prontificar(comanda.getCodigoPedido().toString()))
                .thenReturn(ResponseEntity.ok("STATUS_ATUALIZADO"));

        assertThrows(EntradaInvalidaException.class,
                () -> finalizarComandaUseCase.alterarStatus(comanda.getCodigoPedido()),
                "Erro");

    }

    @Test
    public void testBuscarComandaCodigoPedidoOk()
            throws StatusNuloException, EntradaInvalidaException, ComandaNaoEncontradaException {

        // Gerar massa
        Comanda comanda = new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO);

        // Prepara o mock
        Mockito.when(buscarComandaRepositoryUseCase.buscar(comanda.getCodigoPedido()))
                .thenReturn(Optional.of(comanda));

        // Testo o método
        Comanda resultado = buscaComandaUseCase.buscaComandaPorStatus(comanda.getCodigoPedido());

        // Validações
        assertNotNull(resultado);
        assertEquals(comanda.getCodigoComanda(), resultado.getCodigoComanda());
        assertEquals(comanda.getStatus(), resultado.getStatus());
    }

    @Test
    public void testBuscarComandaCodigoPedidoErrado()
            throws StatusNuloException, EntradaInvalidaException, ComandaNaoEncontradaException {

        // Gerar massa
        Comanda comanda = new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO);
        UUID codigo = UUID.randomUUID();
        // Prepara o mock
        Mockito.when(buscarComandaRepositoryUseCase.buscar(comanda.getCodigoPedido()))
                .thenReturn(Optional.of(comanda));

        // Validações
        assertThrows(ComandaNaoEncontradaException.class,
                () -> buscaComandaUseCase.buscaComandaPorStatus(codigo), "erro");
    }

    @Test
    public void testBuscarComandaStatusOk()
            throws Exception {

        // Gerar massa
        List<Comanda> lista = Arrays.asList(
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO));

        // Prepara o mock
        Mockito.when(buscarPorStatusComandaRepositoryUseCase.buscaComandaPorStatus(StatusComanda.RECEBIDO))
                .thenReturn(lista);

        // Testo o método
        List<Comanda> resultado = buscaRecebidoStatusComandaUseCase.buscaComandaPorStatusRecebido();

        // Validações
        assertNotNull(resultado);
        assertEquals(lista.size(), resultado.size());

    }
}
