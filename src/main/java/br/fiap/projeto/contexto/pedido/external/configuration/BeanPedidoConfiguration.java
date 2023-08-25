package br.fiap.projeto.contexto.pedido.external.configuration;

import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoComandaIntegrationRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoWorkFlowRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoComandaIntegrationRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoWorkFlowRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.gateway.PedidoClienteIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.adapter.gateway.PedidoComandaIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.adapter.gateway.PedidoProdutoIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.adapter.gateway.PedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoClienteIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoComandaIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoProdutoIntegration;
import br.fiap.projeto.contexto.pedido.external.repository.postgres.SpringPedidoRepository;
import br.fiap.projeto.contexto.pedido.usecase.PedidoComandaIntegrationUseCase;
import br.fiap.projeto.contexto.pedido.usecase.PedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.usecase.PedidoQueryUseCase;
import br.fiap.projeto.contexto.pedido.usecase.PedidoWorkFlowUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoClienteIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoComandaIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoProdutoIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoComandaIntegrationUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoQueryUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoWorkFlowUseCase;
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
}