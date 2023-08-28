package br.fiap.projeto.contexto.pedido.infrastructure.configuration;

import br.fiap.projeto.contexto.pedido.domain.port.repository.PedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;
import br.fiap.projeto.contexto.pedido.domain.service.DomainPedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPedidoConfiguration {
    @Bean
    PedidoService pedidoService(PedidoRepositoryPort pedidoRepositoryPort){
        return new DomainPedidoService(pedidoRepositoryPort);
    }
}
