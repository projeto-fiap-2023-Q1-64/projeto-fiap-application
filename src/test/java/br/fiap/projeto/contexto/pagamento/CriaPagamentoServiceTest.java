package br.fiap.projeto.contexto.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.ProcessaNovoPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceAlreadyInProcessException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.mensagens.MensagemDeErro;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IProcessaNovoPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;

public class CriaPagamentoServiceTest {
    private ProcessaNovoPagamentoUseCase novoPagamentoUseCase;
    private IProcessaNovoPagamentoRepositoryAdapterGateway processaNovoPagamentoAdapterGateway;
    private IBuscaPagamentoUseCase buscaPagamentoUseCase;

    @BeforeEach
    public void setUp() {
        processaNovoPagamentoAdapterGateway = mock(IProcessaNovoPagamentoRepositoryAdapterGateway.class);
        buscaPagamentoUseCase = mock(IBuscaPagamentoUseCase.class);
        novoPagamentoUseCase = new ProcessaNovoPagamentoUseCase(processaNovoPagamentoAdapterGateway,
                buscaPagamentoUseCase);
    }

    @Test
    public void testCriaNovoPagamentoQuandoEhPossivelPagar() {
        UUID codigo = UUID.randomUUID();
        // Configure o comportamento simulado do buscaPagamentoUseCase
        when(buscaPagamentoUseCase.findByCodigoPedido(anyString())).thenReturn(new ArrayList<>());

        // Crie um pagamento de exemplo
        Pagamento pagamento = new Pagamento(codigo, "1234", StatusPagamento.APPROVED, new Date(), 100.0);

        // Chame o método que você deseja testar
        Pagamento resultado = novoPagamentoUseCase.criaNovoPagamento(pagamento);

        // Verifique o resultado
        assertNotNull(resultado);
        assertEquals(pagamento, resultado);
    }

    @Test
    public void testCriaNovoPagamentoQuandoNaoEhPossivelPagar() {
        UUID codigo = UUID.randomUUID();
        // Configure o comportamento simulado do buscaPagamentoUseCase para retornar
        // pagamentos existentes
        List<Pagamento> pagamentosExistentes = new ArrayList<>();
        pagamentosExistentes.add(new Pagamento(codigo, "1234", StatusPagamento.APPROVED, new Date(), 100.0));
        when(buscaPagamentoUseCase.findByCodigoPedido(anyString())).thenReturn(pagamentosExistentes);

        // Crie um pagamento de exemplo
        Pagamento pagamento = new Pagamento(codigo, "1234", StatusPagamento.APPROVED, new Date(), 100.0);

        // Tente chamar o método que você deseja testar e capture a exceção
        ResourceAlreadyInProcessException exception = assertThrows(ResourceAlreadyInProcessException.class, () -> {
            novoPagamentoUseCase.criaNovoPagamento(pagamento);
        });

        // Verifique a mensagem da exceção, se necessário
        assertEquals(MensagemDeErro.PAGAMENTO_EXISTENTE.getMessage(), exception.getMessage());
    }
}
