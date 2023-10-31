package br.fiap.projeto.contexto.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.fiap.projeto.contexto.pagamento.adapter.controller.BuscaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;

public class BuscaPagamentoRestAdapterControllerTest {

    @Mock
    private IBuscaPagamentoUseCase buscaPagamentoUseCase;

    private BuscaPagamentoRestAdapterController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new BuscaPagamentoRestAdapterController(buscaPagamentoUseCase);
    }

    @Test
    public void testFindAll() {

        List<Pagamento> pagamentos = Arrays.asList(
                new Pagamento(UUID.randomUUID(), "123", StatusPagamento.APPROVED, new Date(1234454), 50.89),
                new Pagamento(UUID.randomUUID(), "345", StatusPagamento.APPROVED, new Date(1234454), 50.89));

        Mockito.when(buscaPagamentoUseCase.findAll()).thenReturn(pagamentos);

        // Chamar o método da classe controladora
        List<PagamentoDTOResponse> result = controller.findAll();

        // Verificar se o caso de uso foi chamado
        Mockito.verify(buscaPagamentoUseCase).findAll();

        assertEquals(pagamentos.size(), result.size());
        assertEquals(pagamentos.get(0).getCodigoPedido(), result.get(0).getCodigoPedido());
    }

    @Test
    public void testFindByCodigo() {
        UUID codigo = UUID.randomUUID(); // Código de pagamento simulado

        Pagamento pagamento = new Pagamento(UUID.randomUUID(), "123", StatusPagamento.APPROVED, new Date(1234454),
                50.89);

        // Configurar o comportamento simulado do caso de uso para retornar o pagamento
        Mockito.when(buscaPagamentoUseCase.findByCodigo(codigo)).thenReturn(pagamento);

        // Chamar o método da classe controladora
        PagamentoDTOResponse result = controller.findByCodigo(codigo);

        // Verificar se o caso de uso foi chamado
        Mockito.verify(buscaPagamentoUseCase).findByCodigo(codigo);

        assertEquals(pagamento.getCodigoPedido(), result.getCodigoPedido());
    }

}
