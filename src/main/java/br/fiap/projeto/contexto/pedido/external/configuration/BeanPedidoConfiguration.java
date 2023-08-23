package br.fiap.projeto.contexto.pedido.external.configuration;

import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.PedidoWorkFlowRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoWorkFlowRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.gateway.PedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.external.repository.postgres.SpringPedidoRepository;
import br.fiap.projeto.contexto.pedido.usecase.PedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.usecase.PedidoQueryUseCase;
import br.fiap.projeto.contexto.pedido.usecase.PedidoWorkFlowUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoQueryUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoWorkFlowUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPedidoConfiguration {
    @Bean
    IPedidoManagementUseCase pedidoManagementUseCase(IPedidoRepositoryAdapterGateway pedidoRepositoryAdapterGateway){
        return new PedidoManagementUseCase(pedidoRepositoryAdapterGateway);
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
    IPedidoManagementRestAdapterController pedidoManagementRestAdapterController(IPedidoManagementUseCase pedidoUseCase){
        return new PedidoManagementRestAdapterController(pedidoUseCase);
    }
    @Bean
    IPedidoQueryRestAdapterController pedidoQueryRestAdapterController(IPedidoQueryUseCase pedidoQueryUseCase){
        return new PedidoQueryRestAdapterController(pedidoQueryUseCase);
    }
    @Bean
    IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController(IPedidoWorkFlowUseCase iPedidoWorkFlowUseCase){
        return new PedidoWorkFlowRestAdapterController(iPedidoWorkFlowUseCase);
    }
    @Bean
    IPedidoRepositoryAdapterGateway pedidoAdapterGateway(SpringPedidoRepository springPedidoRepository){
        return new PedidoRepositoryAdapterGateway(springPedidoRepository);
    }
}
