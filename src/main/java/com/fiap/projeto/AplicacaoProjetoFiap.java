package com.fiap.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AplicacaoProjetoFiap {

	public static void main(String[] args) {
		SpringApplication.run(AplicacaoProjetoFiap.class, args);
	}

}
