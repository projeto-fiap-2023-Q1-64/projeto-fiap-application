package br.fiap.projeto.contexto.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.BuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IBuscaPagamentoRepositoryAdapterGateway;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

        @InjectMocks
        private BuscaPagamentoUseCase pagamentoUseCase;

        @Mock
        private IBuscaPagamentoRepositoryAdapterGateway buscaPagamentoAdapterGateway = Mockito
                        .mock(IBuscaPagamentoRepositoryAdapterGateway.class);

        Pagamento pagamento;

        @Test
        public void buscaPagamentoValido() {
                UUID codigo = UUID.randomUUID();

                pagamentoUseCase = new BuscaPagamentoUseCase(buscaPagamentoAdapterGateway);

                // Preparação da massa de teste
                pagamento = new Pagamento(codigo, "1234", StatusPagamento.APPROVED, new Date(12345), 1d);

                // Configuração de comportamento simulado para o buscaPagamentoAdapterGateway
                Mockito.when(buscaPagamentoAdapterGateway.findByCodigo(codigo))
                                .thenReturn(pagamento);

                // Execução do método
                Pagamento resultado = pagamentoUseCase.findByCodigo(codigo);

                // Verificação do Resultado
                assertNotNull(resultado);
                assertEquals(pagamento, resultado);

        }

        @Test
        public void buscaPagamentoInValido() {
                UUID codigo = UUID.randomUUID();
                UUID codigo2 = UUID.randomUUID();

                pagamentoUseCase = new BuscaPagamentoUseCase(buscaPagamentoAdapterGateway);

                // Preparação da massa de teste
                pagamento = new Pagamento(codigo, "1234", StatusPagamento.APPROVED, new Date(12345), 1d);

                // Configuração de comportamento simulado para o buscaPagamentoAdapterGateway
                Mockito.when(buscaPagamentoAdapterGateway.findByCodigo(codigo))
                                .thenReturn(pagamento);

                // Execução do método
                assertThrows(ResourceNotFoundException.class, () -> {
                        pagamentoUseCase.findByCodigo(codigo2);
                });
        }

        @Test
        public void buscaTodosPagamentos() {

                UUID codigo = UUID.randomUUID();
                UUID codigo2 = UUID.randomUUID();

                // Preparação da massa de teste
                List<Pagamento> listaPagamento = Arrays.asList(
                                new Pagamento(codigo, "1234", StatusPagamento.APPROVED, new Date(12345), 1d),
                                new Pagamento(codigo2, "1234", StatusPagamento.APPROVED, new Date(12345), 1d));

                pagamentoUseCase = new BuscaPagamentoUseCase(buscaPagamentoAdapterGateway);

                // Configuração de comportamento simulado para o buscaPagamentoAdapterGateway
                Mockito.when(buscaPagamentoAdapterGateway.findAll())
                                .thenReturn(listaPagamento);

                // Execução do método
                List<Pagamento> resultado = pagamentoUseCase.findAll();

                // Verificação do Resultado
                assertNotNull(resultado);
                assertEquals(listaPagamento, resultado);

        }

        @Test
        public void buscaStatusPagamentos() {

                UUID codigo = UUID.randomUUID();
                UUID codigo2 = UUID.randomUUID();
                UUID codigo3 = UUID.randomUUID();

                // Preparação da massa de teste
                List<Pagamento> listaPagamento = Arrays.asList(
                                new Pagamento(codigo, "1234", StatusPagamento.APPROVED, new Date(12345), 1d),
                                new Pagamento(codigo2, "1234", StatusPagamento.CANCELLED, new Date(12345), 1d),
                                new Pagamento(codigo3, "1234", StatusPagamento.CANCELLED, new Date(12345), 1d));

                // pagamentoUseCase = new BuscaPagamentoUseCase(buscaPagamentoAdapterGateway);

                // Configuração de comportamento simulado para o buscaPagamentoAdapterGateway
                Mockito.when(buscaPagamentoAdapterGateway.findByStatusPagamento(Mockito.any(StatusPagamento.class)))
                                .thenReturn(
                                                Arrays.asList(
                                                                new Pagamento(codigo2, "1234",
                                                                                StatusPagamento.CANCELLED,
                                                                                new Date(12345), 1d),
                                                                new Pagamento(codigo3, "1234",
                                                                                StatusPagamento.CANCELLED,
                                                                                new Date(12345), 1d)));
                ;

                // Execução do método
                List<Pagamento> resultado = pagamentoUseCase.findByStatusPagamento(StatusPagamento.CANCELLED);

                // Verificação do Resultado
                assertNotNull(resultado);
                assertEquals(2, resultado.size());

        }

}
