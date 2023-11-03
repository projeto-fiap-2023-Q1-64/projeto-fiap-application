package br.fiap.projeto.contexto.comanda;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaPorComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaPorStatusFinalizadoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaPorStatusPreparacaoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaPorStatusRecebidoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.CriaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.FinalizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.PreparaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.BuscaPorComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.CriarComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.FinalizaStatusComandaDTO;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.PreparaComandaDTO;
import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaDuplicadaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.IntegracaoPedidoException;
import br.fiap.projeto.contexto.comanda.usecase.exception.StatusNuloException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusFinalizadoComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusPreparacaoComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusRecebidoComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.ICriarComandaUseCase;

public class ComandaControllerTest {

    @InjectMocks
    private CriaComandaControllerAdapter criaComandaControllerAdapter;
    @InjectMocks
    private PreparaComandaControllerAdapter preparaComandaControllerAdapter;
    @InjectMocks
    private FinalizaComandaControllerAdapter finalizaComandaControllerAdapter;
    @InjectMocks
    private BuscaPorComandaControllerAdapter buscaPorComandaControllerAdapter;
    @InjectMocks
    private BuscaPorStatusRecebidoComandaControllerAdapter buscaPorStatusRecebidoComandaControllerAdapter;
    @InjectMocks
    private BuscaPorStatusPreparacaoComandaControllerAdapter buscaPorStatusPreparacaoComandaControllerAdapter;
    @InjectMocks
    private BuscaPorStatusFinalizadoComandaControllerAdapter buscaPorStatusFinalizadoComandaControllerAdapter;

    @Mock
    private ICriarComandaUseCase criarComandaUseCase;
    @Mock
    private IAtualizarComandaUseCase atualizaComanda;
    @Mock
    private IBuscaPorComandaUseCase buscaComandaUseCase;
    @Mock
    private IBuscaPorStatusRecebidoComandaUseCase buscaPorStatusRecebidoComandaUseCase;
    @Mock
    private IBuscaPorStatusPreparacaoComandaUseCase buscaPorStatusPreparacaoComandaUseCase;
    @Mock
    private IBuscaPorStatusFinalizadoComandaUseCase buscaPorStatusFinalizadoComandaUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriaComada() throws EntradaInvalidaException, ComandaDuplicadaException {

        // Gerar massa de dados
        CriarComandaDTO criarComandaDTO = new CriarComandaDTO(UUID.randomUUID());
        Comanda comanda = new Comanda(UUID.randomUUID(), criarComandaDTO.getCodigoPedido(),
                StatusComanda.EM_PREPARACAO);

        Mockito.when(criarComandaUseCase.criarComanda(criarComandaDTO.getCodigoPedido())).thenReturn(comanda);

        // Execute o método que você deseja testar
        ComandaDTO comandaDTO = criaComandaControllerAdapter.criaComanda(criarComandaDTO);

        // Verifique se o método retornou o resultado esperado
        Assertions.assertEquals(comanda.getCodigoComanda(), comandaDTO.getCodigoComanda());
        Assertions.assertEquals(comanda.getCodigoPedido(), comandaDTO.getCodigoPedido());

    }

    @Test
    public void testPreparaComada()
            throws EntradaInvalidaException, ComandaDuplicadaException, IntegracaoPedidoException {

        // Gerar massa de dados
        PreparaComandaDTO preparaComandaDTO = new PreparaComandaDTO(UUID.randomUUID());
        Comanda comanda = new Comanda(UUID.randomUUID(), preparaComandaDTO.getCodigoPedido(),
                StatusComanda.EM_PREPARACAO);

        Mockito.when(atualizaComanda.alterarStatus(preparaComandaDTO.getCodigoPedido())).thenReturn(comanda);

        // Execute o método que você deseja testar
        ComandaDTO comandaDTO = preparaComandaControllerAdapter
                .atualizaStatusComanda(preparaComandaDTO.getCodigoPedido());

        // Verifique se o método retornou o resultado esperado
        Assertions.assertEquals(comanda.getCodigoComanda(), comandaDTO.getCodigoComanda());
        Assertions.assertEquals(StatusComanda.EM_PREPARACAO, comandaDTO.getStatus());

    }

    @Test
    public void testFinalizaComada()
            throws EntradaInvalidaException, ComandaDuplicadaException, IntegracaoPedidoException {

        // Gerar massa de dados
        FinalizaStatusComandaDTO finalizaComandaDTO = new FinalizaStatusComandaDTO(UUID.randomUUID());
        Comanda comanda = new Comanda(UUID.randomUUID(), finalizaComandaDTO.getCodigoPedido(),
                StatusComanda.FINALIZADO);

        Mockito.when(atualizaComanda.alterarStatus(finalizaComandaDTO.getCodigoPedido())).thenReturn(comanda);

        // Execute o método que você deseja testar
        ComandaDTO comandaDTO = preparaComandaControllerAdapter
                .atualizaStatusComanda(finalizaComandaDTO.getCodigoPedido());

        // Verifique se o método retornou o resultado esperado
        Assertions.assertEquals(comanda.getCodigoComanda(), comandaDTO.getCodigoComanda());
        Assertions.assertEquals(StatusComanda.FINALIZADO, comandaDTO.getStatus());

    }

    @Test
    public void testBuscaPorComanda()
            throws StatusNuloException, EntradaInvalidaException, ComandaNaoEncontradaException {

        // Gerar Massa
        BuscaPorComandaDTO buscaPorComandaDTO = new BuscaPorComandaDTO(UUID.randomUUID());

        Comanda comanda = new Comanda(UUID.randomUUID(), buscaPorComandaDTO.getCodigoPedido(),
                StatusComanda.FINALIZADO);
        // Mocka o método
        Mockito.when(buscaComandaUseCase.buscaComandaPorStatus(buscaPorComandaDTO.getCodigoPedido()))
                .thenReturn(comanda);

        // Testa o método
        ComandaDTO resultadoComanda = buscaPorComandaControllerAdapter.buscaPorComanda(buscaPorComandaDTO);

        // Verificações
        Assertions.assertEquals(comanda.getCodigoComanda(), resultadoComanda.getCodigoComanda());
    }

    @Test
    public void testeBuscaStatusRecebido() throws Exception {
        // Gerar massa de teste
        List<Comanda> lista = Arrays.asList(
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO));
        // Mockar o método
        Mockito.when(buscaPorStatusRecebidoComandaUseCase.buscaComandaPorStatusRecebido()).thenReturn(lista);

        // Testar o método
        List<ComandaDTO> resultado = buscaPorStatusRecebidoComandaControllerAdapter.buscaPorStatusRecebido();

        // Verificação
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(lista.size(), resultado.size());
    }

    @Test
    public void testeBuscaStatusPreparacao() throws Exception {
        // Gerar massa de teste
        List<Comanda> lista = Arrays.asList(
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO));
        // Mockar o método
        Mockito.when(buscaPorStatusPreparacaoComandaUseCase.buscaComandaPorStatusPreparacao()).thenReturn(lista);

        // Testar o método
        List<ComandaDTO> resultado = buscaPorStatusPreparacaoComandaControllerAdapter.buscaPorStatusPreparacao();

        // Verificação
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(lista.size(), resultado.size());
    }

    @Test
    public void testeBuscaStatusFinalizado() throws Exception {
        // Gerar massa de teste
        List<Comanda> lista = Arrays.asList(
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO));
        // Mockar o método
        Mockito.when(buscaPorStatusFinalizadoComandaUseCase.buscaComandaPorStatusFinalizado()).thenReturn(lista);

        // Testar o método
        List<ComandaDTO> resultado = buscaPorStatusFinalizadoComandaControllerAdapter.buscaPorStatusFinalizado();

        // Verificação
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(lista.size(), resultado.size());
    }
}
