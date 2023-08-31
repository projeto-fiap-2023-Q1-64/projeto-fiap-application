package br.fiap.projeto.contexto.identificacao.external.config;

import br.fiap.projeto.contexto.identificacao.adapter.controller.ClienteRestAdapterController;
import br.fiap.projeto.contexto.identificacao.adapter.controller.port.IClienteRestAdapterController;
import br.fiap.projeto.contexto.identificacao.adapter.gateway.ClienteRepositoryAdapterGateway;
import br.fiap.projeto.contexto.identificacao.external.repository.postgres.SpringClienteRepository;
import br.fiap.projeto.contexto.identificacao.usecase.GestaoClienteUseCase;
import br.fiap.projeto.contexto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;
import br.fiap.projeto.contexto.identificacao.usecase.port.IGestaoClienteUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    public IGestaoClienteUsecase gestaoClienteUsecase(IClienteRepositoryAdapterGateway clienteRepository) {
        return new GestaoClienteUseCase(clienteRepository);
    }

    @Bean
    public IClienteRestAdapterController clienteRestAdapterController(IGestaoClienteUsecase gestaoClienteUsecase) {
        return new ClienteRestAdapterController(gestaoClienteUsecase);
    }

    @Bean
    public IClienteRepositoryAdapterGateway clienteRepositoryAdapterGateway(SpringClienteRepository springClienteRepository) {
        return new ClienteRepositoryAdapterGateway(springClienteRepository);
    }
}
