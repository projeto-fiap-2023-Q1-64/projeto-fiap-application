package br.fiap.projeto.contexto.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.fiap.projeto.contexto.pagamento.adapter.controller.AtualizaStatusPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.gateway.BuscaPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.BuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;

public class AtualizaStatusPagamentoRestAdapterControllerTest {

    @InjectMocks
    private AtualizaStatusPagamentoRestAdapterController atualizaStatusPagamentoRestAdapterController;
    @InjectMocks
    private BuscaPagamentoUseCase pagamentoUseCase;

    @Mock
    private IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase;
    @Mock
    private BuscaPagamentoRepositoryAdapterGateway buscaPagamentoAdapterGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAtualizaStatusPagamentoParaEmProcessamento() {

        PagamentoDTORequest pagamentoDTORequest = new PagamentoDTORequest(UUID.randomUUID(), "123",
                StatusPagamento.IN_PROCESS, new Date(12345), 10d);

        atualizaStatusPagamentoRestAdapterController.atualizaStatusPagamento(pagamentoDTORequest);

        Mockito.verify(atualizaStatusPagamentoUsecase).atualizaStatusPagamento(pagamentoDTORequest.getCodigoPedido(),
                StatusPagamento.IN_PROCESS);
    }

    @Test
    public void buscaStatusPagamentos() {

        // Preparação da massa de teste
        List<Pagamento> listaPagamento = Arrays.asList(
                // new Pagamento(codigo, "1234", StatusPagamento.APPROVED, new Date(12345), 1d),
                new Pagamento(UUID.randomUUID(), "1234", StatusPagamento.CANCELLED, new Date(12345),
                        1d),
                new Pagamento(UUID.randomUUID(), "1234", StatusPagamento.CANCELLED, new Date(12345),
                        1d));

        // Configuração de comportamento simulado para o buscaPagamentoAdapterGateway
        when(buscaPagamentoAdapterGateway.findByStatusPagamento(any(StatusPagamento.class)))
                .thenReturn(listaPagamento);

        // Execução do método
        List<Pagamento> resultado = pagamentoUseCase.findByStatusPagamento(StatusPagamento.CANCELLED);

        // Verificação do Resultado
        assertNotNull(resultado);
        assertEquals(2, resultado.size());

    }
}
