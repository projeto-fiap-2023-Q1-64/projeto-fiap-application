package br.fiap.projeto.contexto.pagamento.external.configuration;

import br.fiap.projeto.contexto.pagamento.usecase.port.repository.PagamentoRepositoryPort;
import br.fiap.projeto.contexto.pagamento.usecase.port.service.PagamentoServicePort;
import br.fiap.projeto.contexto.pagamento.usecase.DomainPagamentoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPagamentoConfiguration {


    @Bean
    PagamentoServicePort pagamentoService(PagamentoRepositoryPort pagamentoRepositoryPort){
        return new DomainPagamentoService(pagamentoRepositoryPort);
    }

    //TODO registro outras injeções
}