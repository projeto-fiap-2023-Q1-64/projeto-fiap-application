package br.fiap.projeto.config;

import br.fiap.projeto.contexto.pagamento.domain.port.repository.PagamentoRepositoryPort;
import br.fiap.projeto.contexto.pagamento.domain.port.service.PagamentoServicePort;
import br.fiap.projeto.contexto.pagamento.domain.service.DomainPagamentoService;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepositoryPort;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoServicePort;
import br.fiap.projeto.contexto.produto.domain.service.DomainProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProdutoServicePort produtoService(ProdutoRepositoryPort produtoRepositoryPort) {
        return new DomainProdutoService(produtoRepositoryPort);
    }

    @Bean
    PagamentoServicePort pagamentoService(PagamentoRepositoryPort pagamentoRepositoryPort){
        return new DomainPagamentoService(pagamentoRepositoryPort);
    }
}
