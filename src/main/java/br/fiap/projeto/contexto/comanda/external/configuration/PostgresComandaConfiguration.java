package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.fiap.projeto.contexto.comanda.external")
@EntityScan("br.fiap.projeto.contexto.comanda.external.repository.entity")
public class PostgresComandaConfiguration {
}
