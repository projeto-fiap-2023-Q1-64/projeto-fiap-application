package br.fiap.projeto.contexto.pedido.external.configuration;

import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.PedidoService;
import br.fiap.projeto.contexto.pedido.usecase.DomainPedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPedidoConfiguration {
    @Bean
    PedidoService pedidoService(IPedidoRepositoryAdapterGateway IPedidoRepositoryAdapterGateway){
        return new DomainPedidoService(IPedidoRepositoryAdapterGateway);
    }
}
