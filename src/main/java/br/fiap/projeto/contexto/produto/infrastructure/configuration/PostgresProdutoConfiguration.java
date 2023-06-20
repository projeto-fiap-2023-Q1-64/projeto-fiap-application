package br.fiap.projeto.contexto.produto.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.fiap.projeto.contexto.produto.infrastructure")
@EntityScan("br.fiap.projeto.contexto.produto.infrastructure.entity")
public class PostgresProdutoConfiguration {
}
