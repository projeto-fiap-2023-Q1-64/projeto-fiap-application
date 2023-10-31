package br.fiap.projeto.contexto.pagamento;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.fiap.projeto.contexto.pagamento.adapter.controller.AtualizaStatusPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;

public class AtualizaStatusPagamentoRestAdapterControllerTest {

    @Mock
    private IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase;

    @InjectMocks
    private AtualizaStatusPagamentoRestAdapterController atualizaStatusPagamentoRestAdapterController;

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

    // Como não possui retorno ele simula a entrada nos métodos.
}
