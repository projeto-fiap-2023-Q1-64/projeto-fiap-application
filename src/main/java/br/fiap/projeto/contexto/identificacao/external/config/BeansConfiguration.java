package br.fiap.projeto.contexto.identificacao.external.config;

import br.fiap.projeto.contexto.identificacao.usecase.port.repository.ClienteRepository;
import br.fiap.projeto.contexto.identificacao.usecase.port.service.ClienteService;
import br.fiap.projeto.contexto.identificacao.usecase.ClienteServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteServiceImpl(clienteRepository);
    }
}
