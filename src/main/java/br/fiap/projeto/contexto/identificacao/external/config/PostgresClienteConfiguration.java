package br.fiap.projeto.contexto.identificacao.external.config;

import br.fiap.projeto.contexto.identificacao.external.repository.entity.ClienteEntity;
import br.fiap.projeto.contexto.identificacao.external.repository.postgres.SpringClienteRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = SpringClienteRepository.class)
@EntityScan(basePackageClasses = ClienteEntity.class)
public class PostgresClienteConfiguration {

}
