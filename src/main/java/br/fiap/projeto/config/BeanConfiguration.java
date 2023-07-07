package br.fiap.projeto.config;

import br.fiap.projeto.contexto.pagamento.domain.port.repository.PagamentoRepositoryPort;
import br.fiap.projeto.contexto.pagamento.domain.port.service.PagamentoServicePort;
import br.fiap.projeto.contexto.pagamento.domain.service.DomainPagamentoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    PagamentoServicePort pagamentoService(PagamentoRepositoryPort pagamentoRepositoryPort){
        return new DomainPagamentoService(pagamentoRepositoryPort);
    }
}
