package br.fiap.projeto.contexto.pedido.external.configuration;

import br.fiap.projeto.contexto.pedido.adapter.controller.*;
import br.fiap.projeto.contexto.pedido.adapter.controller.port.*;
import br.fiap.projeto.contexto.pedido.adapter.gateway.*;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoClienteIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoComandaIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoPagamentoIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoProdutoIntegration;
import br.fiap.projeto.contexto.pedido.external.repository.postgres.SpringPedidoRepository;
import br.fiap.projeto.contexto.pedido.usecase.*;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.*;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPedidoConfiguration {
    @Bean
    IPedidoManagementUseCase pedidoManagementUseCase(IPedidoRepositoryAdapterGateway pedidoRepositoryAdapterGateway,
                                                     IPedidoProdutoIntegrationAdapterGateway pedidoProdutoIntegrationAdapterGateway,
                                                     IPedidoClienteIntegrationAdapterGateway pedidoClienteIntegration){
        return new PedidoManagementUseCase(pedidoRepositoryAdapterGateway,
                pedidoProdutoIntegrationAdapterGateway,
                pedidoClienteIntegration);
    }
    @Bean
    IPedidoQueryUseCase pedidoQueryUseCase(IPedidoRepositoryAdapterGateway pedidoRepositoryAdapterGateway){
        return new PedidoQueryUseCase(pedidoRepositoryAdapterGateway);
    }
    @Bean
    IPedidoWorkFlowUseCase pedidoWorkFlowUseCase(IPedidoRepositoryAdapterGateway pedidoRepositoryAdapterGateway){
        return new PedidoWorkFlowUseCase(pedidoRepositoryAdapterGateway);
    }
    @Bean
    IPedidoComandaIntegrationUseCase pedidoComandaIntegrationUseCase(IPedidoComandaIntegrationAdapterGateway pedidoComandaIntegrationAdapterGateway,
                                                                     IPedidoWorkFlowUseCase pedidoWorkFlowUseCase){
        return new PedidoComandaIntegrationUseCase(pedidoComandaIntegrationAdapterGateway,
                pedidoWorkFlowUseCase);
    }
    @Bean
    IPedidoPagamentoIntegrationUseCase pedidoPagamentoIntegrationUseCase(IPedidoRepositoryAdapterGateway pedidoRepositoryAdapterGateway,
                                                                         IPedidoPagamentoIntegrationAdapterGateway pedidoPagamentoIntegrationAdapterGateway,
                                                                         IPedidoWorkFlowUseCase pedidoWorkFlowUseCase,
                                                                         IPedidoComandaIntegrationUseCase pedidoComandaIntegrationUseCase){
        return new PedidoPagamentoIntegrationUseCase(pedidoRepositoryAdapterGateway,
                pedidoPagamentoIntegrationAdapterGateway,
                pedidoWorkFlowUseCase,
                pedidoComandaIntegrationUseCase);
    }
    @Bean
    IPedidoManagementRestAdapterController pedidoManagementRestAdapterController(IPedidoManagementUseCase pedidoUseCase){
        return new PedidoManagementRestAdapterController(pedidoUseCase);
    }
    @Bean
    IPedidoQueryRestAdapterController pedidoQueryRestAdapterController(IPedidoQueryUseCase pedidoQueryUseCase){
        return new PedidoQueryRestAdapterController(pedidoQueryUseCase);
    }
    @Bean
    IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController(IPedidoWorkFlowUseCase PedidoWorkFlowUseCase){
        return new PedidoWorkFlowRestAdapterController(PedidoWorkFlowUseCase);
    }
    @Bean
    IPedidoComandaIntegrationRestAdapterController pedidoComandaIntegrationRestAdapterController(IPedidoComandaIntegrationUseCase pedidoComandaIntegrationUseCase){
        return new PedidoComandaIntegrationRestAdapterController(pedidoComandaIntegrationUseCase);
    }
    @Bean
    IPedidoPagamentoIntegrationRestAdapterController pedidoPagamentoIntegrationRestAdapterController(IPedidoPagamentoIntegrationUseCase pedidoPagamentoIntegrationUseCase){
        return new PedidoPagamentoIntegrationRestAdapterController(pedidoPagamentoIntegrationUseCase);
    }
    @Bean
    IPedidoRepositoryAdapterGateway pedidoAdapterGateway(SpringPedidoRepository springPedidoRepository){
        return new PedidoRepositoryAdapterGateway(springPedidoRepository);
    }
    @Bean
    IPedidoProdutoIntegrationAdapterGateway pedidoProdutoIntegrationAdapterGateway(PedidoProdutoIntegration pedidoProdutoIntegration){
        return new PedidoProdutoIntegrationAdapterGateway(pedidoProdutoIntegration);
    }
    @Bean
    IPedidoClienteIntegrationAdapterGateway pedidoClienteIntegrationAdapterGateway(PedidoClienteIntegration pedidoClienteIntegration){
        return new PedidoClienteIntegrationAdapterGateway(pedidoClienteIntegration);
    }
    @Bean
    IPedidoComandaIntegrationAdapterGateway pedidoComandaIntegrationAdapterGateway(PedidoComandaIntegration pedidoComandaIntegration){
        return new PedidoComandaIntegrationAdapterGateway(pedidoComandaIntegration);
    }
    @Bean
    IPedidoPagamentoIntegrationAdapterGateway pedidoPagamentoIntegrationAdapterGateway(PedidoPagamentoIntegration pagamentoIntegration){
        return new PedidoPagamentoIntegrationAdapterGateway(pagamentoIntegration);
    }
}