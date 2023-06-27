package br.fiap.projeto.config;

import br.fiap.projeto.contexto.pagamento.domain.port.repository.PagamentoRepositoryPort;
import br.fiap.projeto.contexto.pagamento.domain.port.service.PagamentoServicePort;
import br.fiap.projeto.contexto.pagamento.domain.service.DomainPagamentoService;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepository;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoService;
import br.fiap.projeto.contexto.produto.domain.service.DomainProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProdutoService produtoService(ProdutoRepository produtoRepository) {
        return new DomainProdutoService(produtoRepository);
    }

    @Bean
    PagamentoServicePort pagamentoService(PagamentoRepositoryPort pagamentoRepositoryPort){
        return new DomainPagamentoService(pagamentoRepositoryPort);
    }
}
