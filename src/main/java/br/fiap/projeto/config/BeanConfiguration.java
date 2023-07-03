package br.fiap.projeto.config;

import br.fiap.projeto.contexto.pedido.domain.port.repository.PedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;
import br.fiap.projeto.contexto.pedido.domain.service.DomainPedidoService;
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
    PedidoService pedidoService(PedidoRepositoryPort pedidoRepositoryPort){
        return new DomainPedidoService(pedidoRepositoryPort);
    }
}
