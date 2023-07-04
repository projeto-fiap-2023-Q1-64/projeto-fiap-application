package br.fiap.projeto.contexto.identificacao.infrastructure.config;

import br.fiap.projeto.contexto.identificacao.domain.port.repository.ClienteRepository;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import br.fiap.projeto.contexto.identificacao.domain.service.ClienteServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteServiceImpl(clienteRepository);
    }
}
