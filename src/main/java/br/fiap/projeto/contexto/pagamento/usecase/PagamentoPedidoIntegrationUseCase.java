package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.entity.integration.PagamentoPedidoResponse;
import br.fiap.projeto.contexto.pagamento.external.integration.exceptions.InvalidOperationIntegrationException;
import br.fiap.projeto.contexto.pagamento.external.integration.exceptions.PagamentoPedidoIntegrationException;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.mensagens.MensagemDeErro;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IPagamentoPedidoIntegrationGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IPagamentoPedidoIntegrationUseCase;
import feign.FeignException;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PagamentoPedidoIntegrationUseCase implements IPagamentoPedidoIntegrationUseCase {

    private final IPagamentoPedidoIntegrationGateway pagamentoPedidoIntegrationGateway;

    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;

    private ScheduledExecutorService scheduler = null;

    public PagamentoPedidoIntegrationUseCase(IPagamentoPedidoIntegrationGateway pagamentoPedidoIntegrationGateway, IBuscaPagamentoUseCase buscaPagamentoUseCase) {
        this.pagamentoPedidoIntegrationGateway = pagamentoPedidoIntegrationGateway;
        this.buscaPagamentoUseCase  = buscaPagamentoUseCase;
    }

   @Override
    public void atualizarPagamentoPedido(Pagamento pagamento) {
        PagamentoPedidoResponse response = new PagamentoPedidoResponse(pagamento.getCodigoPedido(), pagamento.getStatus().name(), LocalDateTime.now());
        pagamentoPedidoIntegrationGateway.atualizaStatusPagamentoPedido(response);
    }

    @Override
    public void scheduleAtualizaPagamentoPedido(String codigoPedido) {
        Pagamento pagamento = getPagamento(codigoPedido);
        this.scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            try{
                atualizarPagamentoPedido(pagamento);
            }catch(FeignException fe){
                fe.printStackTrace();
                throw new PagamentoPedidoIntegrationException(MensagemDeErro.ERRO_INTEGRACAO.getMessage());
            }catch(Exception e){
                e.printStackTrace();
                throw new InvalidOperationIntegrationException(MensagemDeErro.ENVIO_ATUALIZACAO_STATUS_INTEGRACAO_FALHA.getMessage());
            } finally {
                shutDownScheduler();
            }
        }, 3, TimeUnit.SECONDS);
    }

    private Pagamento getPagamento(String codigoPedido) {
        Pagamento pagamento = buscaPagamentoUseCase.findByCodigoPedido(codigoPedido)
                .stream()
                .filter(p -> p.getStatus().equals(StatusPagamento.APPROVED) || p.getStatus().equals(StatusPagamento.CANCELLED))
                .findFirst()
                .get();
        System.out.println("Recuperando Pagamento dentro do Schedule");
        System.out.println(pagamento);
        return pagamento;
    }

    @Override
    public void shutDownScheduler() {
        System.out.println("Dentro od ShutDown");
        scheduler.shutdown();
    }
}
