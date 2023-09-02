package br.fiap.projeto.contexto.pagamento.external.configuration;

import br.fiap.projeto.contexto.pagamento.adapter.controller.AtualizaStatusPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.BuscaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.EnviaPagamentoAoGatewayRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.ProcessaNovoPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IAtualizaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IBuscaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IEnviaPagamentoGatewayRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IProcessaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.gateway.AtualizaStatusPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.adapter.gateway.BuscaPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.adapter.gateway.PagamentoPedidoIntegrationGateway;
import br.fiap.projeto.contexto.pagamento.adapter.gateway.ProcessaNovoPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.external.integration.IPedidoIntegration;
import br.fiap.projeto.contexto.pagamento.external.repository.postgres.SpringPagamentoRepository;
import br.fiap.projeto.contexto.pagamento.usecase.AtualizaStatusPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.BuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.EnviaPagamentoAoGatewayPagamentosUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.ProcessaNovoPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IAtualizaStatusPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IBuscaPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IPagamentoPedidoIntegrationGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IProcessaNovoPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IEnviaPagamentoAoGatewayPagamentosUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IProcessaNovoPagamentoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPagamentoConfiguration {

    @Bean
    IBuscaPagamentoRepositoryAdapterGateway pagamentoAdapterGateway(SpringPagamentoRepository springPagamentoRepository){
        return new BuscaPagamentoRepositoryAdapterGateway(springPagamentoRepository);
    }

    @Bean
    IBuscaPagamentoRestAdapterController pagamentoAdapterController(IBuscaPagamentoUseCase buscaPagamentoUseCase){
        return  new BuscaPagamentoRestAdapterController(buscaPagamentoUseCase);
    }

    @Bean
    IBuscaPagamentoUseCase buscaPagamentoUseCase(IBuscaPagamentoRepositoryAdapterGateway pagamentoAdapterGateway){
        return new BuscaPagamentoUseCase(pagamentoAdapterGateway);
    }

    @Bean
    IProcessaNovoPagamentoRepositoryAdapterGateway processaNovoPagamentoAdapterGateway(SpringPagamentoRepository springPagamentoRepository, IBuscaPagamentoUseCase buscaPagamentoUseCase){
        return new ProcessaNovoPagamentoRepositoryAdapterGateway(springPagamentoRepository, buscaPagamentoUseCase);
    }

    @Bean
    IProcessaPagamentoRestAdapterController processaNovoPagamentoAdapterController(IProcessaNovoPagamentoUseCase processaNovoPagamentoUseCase){
        return new ProcessaNovoPagamentoRestAdapterController(processaNovoPagamentoUseCase);
    }

    @Bean
    IProcessaNovoPagamentoUseCase processaNovoPagamentoUseCase(IProcessaNovoPagamentoRepositoryAdapterGateway processaNovoPagamentoAdapterGateway, IBuscaPagamentoUseCase buscaPagamentoUseCase){
        return new ProcessaNovoPagamentoUseCase(processaNovoPagamentoAdapterGateway, buscaPagamentoUseCase);
    }

    @Bean
    IAtualizaStatusPagamentoRepositoryAdapterGateway atualizaStatusPagamentoAdapterGateway(SpringPagamentoRepository springPagamentoRepository){
        return new AtualizaStatusPagamentoRepositoryAdapterGateway(springPagamentoRepository);
    }

    @Bean
    IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase(IAtualizaStatusPagamentoRepositoryAdapterGateway atualizaStatusPagamentoAdapterGateway,
                                                                   IBuscaPagamentoUseCase buscaPagamentoUseCase){
        return new AtualizaStatusPagamentoUseCase(atualizaStatusPagamentoAdapterGateway,
                buscaPagamentoUseCase);
    }

    @Bean
    IEnviaPagamentoAoGatewayPagamentosUseCase enviaPagamentoAoGatewayPagamentosUseCase(IBuscaPagamentoUseCase buscaPagamentoUseCase,
                                                                                       IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase){
        return new EnviaPagamentoAoGatewayPagamentosUseCase(buscaPagamentoUseCase, atualizaStatusPagamentoUsecase);
    }

    @Bean
    IEnviaPagamentoGatewayRestAdapterController enviaPagamentoGatewayRestAdapterController(IEnviaPagamentoAoGatewayPagamentosUseCase enviaPagamentoAoGatewayPagamentosUseCase){
        return new EnviaPagamentoAoGatewayRestAdapterController(enviaPagamentoAoGatewayPagamentosUseCase);
    }

    @Bean
    IAtualizaPagamentoRestAdapterController atualizaPagamentoRestAdapterController(IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase){
        return new AtualizaStatusPagamentoRestAdapterController(atualizaStatusPagamentoUsecase);
    }
    @Bean
    IPagamentoPedidoIntegrationGateway pagamentoPedidoIntegrationGateway(IPedidoIntegration pedidoIntegration){
        return new PagamentoPedidoIntegrationGateway(pedidoIntegration);
    }
}