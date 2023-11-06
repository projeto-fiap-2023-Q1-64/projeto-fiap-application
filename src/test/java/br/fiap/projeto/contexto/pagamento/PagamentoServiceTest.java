package br.fiap.projeto.contexto.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.fiap.projeto.contexto.pagamento.adapter.controller.AtualizaStatusPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.gateway.BuscaPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.BuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.ProcessaNovoPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceAlreadyInProcessException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.mensagens.MensagemDeErro;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IProcessaNovoPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

        @InjectMocks
        private BuscaPagamentoUseCase pagamentoUseCase;
        // @InjectMocks
        private ProcessaNovoPagamentoUseCase novoPagamentoUseCase;
        @InjectMocks
        private AtualizaStatusPagamentoRestAdapterController atualizaStatusPagamentoRestAdapterController;

        @Mock
        private BuscaPagamentoRepositoryAdapterGateway buscaPagamentoAdapterGateway;
        @Mock
        private IProcessaNovoPagamentoRepositoryAdapterGateway processaNovoPagamentoAdapterGateway;
        @Mock
        private IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase;

        Pagamento pagamento;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.initMocks(this);
                novoPagamentoUseCase = new ProcessaNovoPagamentoUseCase(processaNovoPagamentoAdapterGateway,
                                pagamentoUseCase);
        }

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

        // @Test
        // public void buscaStatusPagamentos() {

        // // Preparação da massa de teste
        // List<Pagamento> listaPagamento3 = Arrays.asList(
        // // new Pagamento(codigo, "1234", StatusPagamento.APPROVED, new Date(12345),
        // 1d),
        // new Pagamento(UUID.randomUUID(), "1234", StatusPagamento.CANCELLED, new
        // Date(12345),
        // 1d),
        // new Pagamento(UUID.randomUUID(), "1234", StatusPagamento.CANCELLED, new
        // Date(12345),
        // 1d));

        // // Configuração de comportamento simulado para o buscaPagamentoAdapterGateway
        // when(buscaPagamentoAdapterGateway.findByStatusPagamento(any(StatusPagamento.class)))
        // .thenReturn(listaPagamento3);

        // // Execução do método
        // List<Pagamento> resultado =
        // pagamentoUseCase.findByStatusPagamento(StatusPagamento.CANCELLED);

        // // Verificação do Resultado
        // assertNotNull(resultado);
        // assertEquals(2, resultado.size());

        // }

        @Test
        public void testCriaNovoPagamentoQuandoEhPossivelPagar() {
                UUID codigo = UUID.randomUUID();
                // Configure o comportamento simulado do buscaPagamentoUseCase
                when(pagamentoUseCase.findByCodigoPedido(anyString())).thenReturn(new ArrayList<>());

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
                when(pagamentoUseCase.findByCodigoPedido(anyString())).thenReturn(pagamentosExistentes);

                // Crie um pagamento de exemplo
                Pagamento pagamento = new Pagamento(codigo, "1234", StatusPagamento.APPROVED, new Date(), 100.0);

                // Tente chamar o método que você deseja testar e capture a exceção
                ResourceAlreadyInProcessException exception = assertThrows(ResourceAlreadyInProcessException.class,
                                () -> {
                                        novoPagamentoUseCase.criaNovoPagamento(pagamento);
                                });

                // Verifique a mensagem da exceção, se necessário
                assertEquals(MensagemDeErro.PAGAMENTO_EXISTENTE.getMessage(), exception.getMessage());
        }

        // @Test
        // public void testAtualizaStatusPagamentoParaEmProcessamento() {

        // PagamentoDTORequest pagamentoDTORequest = new
        // PagamentoDTORequest(UUID.randomUUID(), "123",
        // StatusPagamento.IN_PROCESS, new Date(12345), 10d);

        // atualizaStatusPagamentoRestAdapterController.atualizaStatusPagamento(pagamentoDTORequest);

        // Mockito.verify(atualizaStatusPagamentoUsecase).atualizaStatusPagamento(
        // pagamentoDTORequest.getCodigoPedido(),
        // StatusPagamento.IN_PROCESS);
        // }

}
