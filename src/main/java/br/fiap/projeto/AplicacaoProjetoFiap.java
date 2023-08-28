package br.fiap.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableJpaRepositories
public class AplicacaoProjetoFiap {

	public static void main(String[] args) {
		SpringApplication.run(AplicacaoProjetoFiap.class, args);
	}

}
