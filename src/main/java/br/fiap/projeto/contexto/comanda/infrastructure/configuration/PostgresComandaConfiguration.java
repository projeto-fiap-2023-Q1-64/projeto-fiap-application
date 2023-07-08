package br.fiap.projeto.contexto.comanda.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.fiap.projeto.contexto.comanda.infrastructure")
@EntityScan("br.fiap.projeto.contexto.comanda.infrastructure.entity")
public class PostgresComandaConfiguration {
}
