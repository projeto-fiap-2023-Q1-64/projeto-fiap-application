package br.fiap.projeto.contexto.pagamento.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.fiap.projeto.contexto.pagamento.infrastructure")
@EntityScan("br.fiap.projeto.contexto.pagamento.infrastructure.entity")
public class PostgresPagamentoConfiguration {

}
