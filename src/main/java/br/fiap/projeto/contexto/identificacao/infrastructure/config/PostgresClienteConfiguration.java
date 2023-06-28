package br.fiap.projeto.contexto.identificacao.infrastructure.config;

import br.fiap.projeto.contexto.identificacao.infrastructure.entity.ClienteEntity;
import br.fiap.projeto.contexto.identificacao.infrastructure.repository.SpringDataClienteRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = SpringDataClienteRepository.class)
@EntityScan(basePackageClasses = ClienteEntity.class)
public class PostgresClienteConfiguration {

}
