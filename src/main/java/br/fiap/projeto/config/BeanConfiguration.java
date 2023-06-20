package br.fiap.projeto.config;

import br.fiap.projeto.AplicacaoProjetoFiap;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepository;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoService;
import br.fiap.projeto.contexto.produto.domain.service.DomainProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProdutoService produtoService(ProdutoRepository produtoRepository) {
        return new DomainProdutoService(produtoRepository);
    }
}
