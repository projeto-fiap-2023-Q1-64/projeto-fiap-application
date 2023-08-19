package br.fiap.projeto.contexto.pedido.external.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.fiap.projeto.contexto.pedido.external")
@EntityScan("br.fiap.projeto.contexto.pedido.infrastructure.entity")
public class PostgresPedidoConfiguration {
}
