package br.fiap.projeto.contexto.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.fiap.projeto.contexto.pagamento.adapter.controller.ProcessaNovoPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoNovoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IProcessaNovoPagamentoUseCase;

public class ProcessaNovoPagamentoRestAdapterControllerTest {

    @Mock
    private IProcessaNovoPagamentoUseCase processaNovoPagamentoUseCase;

    private ProcessaNovoPagamentoRestAdapterController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new ProcessaNovoPagamentoRestAdapterController(processaNovoPagamentoUseCase);
    }

    @Test
    public void testCriaNovoPagamento() {
        // Crie um objeto de PedidoAPagarDTORequest para usar no teste
        PedidoAPagarDTORequest pedidoRequest = new PedidoAPagarDTORequest(/* preencha os campos necessários */);

        // Crie um objeto de Pagamento que o caso de uso deve retornar
        Pagamento pagamento = new Pagamento(UUID.randomUUID(), "123", StatusPagamento.APPROVED, new Date(1234454),
                50.89);

        // Configure o comportamento simulado do caso de uso para retornar o pagamento
        Mockito.when(processaNovoPagamentoUseCase.criaNovoPagamento(Mockito.any(Pagamento.class)))
                .thenReturn(pagamento);

        // Chame o método da classe controladora
        PagamentoNovoDTOResponse response = controller.criaNovoPagamento(pedidoRequest);

        // Verifique se o caso de uso foi chamado com os parâmetros corretos
        Mockito.verify(processaNovoPagamentoUseCase).criaNovoPagamento(Mockito.any(Pagamento.class));

        // Faça as verificações necessárias no response
        assertEquals(pagamento.getCodigoPedido(), response.getCodigoPedido());

    }
}
