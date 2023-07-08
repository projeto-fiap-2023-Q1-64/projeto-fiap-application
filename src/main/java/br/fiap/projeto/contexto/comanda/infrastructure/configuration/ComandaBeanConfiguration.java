package br.fiap.projeto.contexto.comanda.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.domain.port.repository.ComandaRepositoryPort;
import br.fiap.projeto.contexto.comanda.domain.port.service.ComandaServicePort;
import br.fiap.projeto.contexto.comanda.domain.service.DomainComandaService;
import br.fiap.projeto.contexto.comanda.infrastructure.integration.ComandaPedidoIntegration;

@Configuration
public class ComandaBeanConfiguration {

    @Bean
    ComandaServicePort comandaService(ComandaRepositoryPort comandaRepositoryPort,
            ComandaPedidoIntegration comandaPedidoIntegration) {
        return new DomainComandaService(comandaRepositoryPort, comandaPedidoIntegration);
    }
}
