package br.fiap.projeto.contexto.produto.external.configuration;

import br.fiap.projeto.contexto.produto.adapter.controller.ProdutoAdapterController;
import br.fiap.projeto.contexto.produto.adapter.gateway.ProdutoAdapterGateway;
import br.fiap.projeto.contexto.produto.adapter.interfaces.IProdutoAdapterController;
import br.fiap.projeto.contexto.produto.external.api.ProdutoApiController;
import br.fiap.projeto.contexto.produto.external.repository.postgres.SpringProdutoRepository;
import br.fiap.projeto.contexto.produto.usecase.ProdutoUseCase;
import br.fiap.projeto.contexto.produto.usecase.port.IProdutoAdapterGateway;
import br.fiap.projeto.contexto.produto.usecase.port.service.IProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanProdutoConfiguration {
    @Bean
    IProdutoUseCase produtoUseCase(IProdutoAdapterGateway produtoAdapterGateway) {
        return new ProdutoUseCase(produtoAdapterGateway);
    }

    @Bean
    IProdutoAdapterGateway produtoAdapterGateway(SpringProdutoRepository springProdutoRepository) {
        return new ProdutoAdapterGateway(springProdutoRepository);
    }

    @Bean
    IProdutoAdapterController produtoAdapterController(IProdutoUseCase produtoUseCase) {
        return new ProdutoAdapterController(produtoUseCase);
    }
}
