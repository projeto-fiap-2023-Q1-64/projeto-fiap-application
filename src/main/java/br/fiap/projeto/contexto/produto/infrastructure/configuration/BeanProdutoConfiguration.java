package br.fiap.projeto.contexto.produto.infrastructure.configuration;

import br.fiap.projeto.contexto.identificacao.domain.port.repository.ClienteRepository;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import br.fiap.projeto.contexto.identificacao.domain.service.ClienteServiceImpl;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepositoryPort;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoServicePort;
import br.fiap.projeto.contexto.produto.domain.service.DomainProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanProdutoConfiguration {
    @Bean
    ProdutoServicePort produtoService(ProdutoRepositoryPort produtoRepositoryPort) {
        return new DomainProdutoService(produtoRepositoryPort);
    }
}
