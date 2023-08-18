package br.fiap.projeto.contexto.produto.external.configuration;

import br.fiap.projeto.contexto.produto.adapter.controller.ProdutoRestAdapterController;
import br.fiap.projeto.contexto.produto.adapter.gateway.ProdutoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.produto.adapter.controller.port.IProdutoRestAdapterController;
import br.fiap.projeto.contexto.produto.external.repository.postgres.SpringProdutoRepository;
import br.fiap.projeto.contexto.produto.usecase.GestaoProdutoUseCase;
import br.fiap.projeto.contexto.produto.usecase.port.IProdutoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.produto.usecase.port.IGestaoProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanProdutoConfiguration {
    @Bean
    IGestaoProdutoUseCase produtoUseCase(IProdutoRepositoryAdapterGateway produtoAdapterGateway) {
        return new GestaoProdutoUseCase(produtoAdapterGateway);
    }

    @Bean
    IProdutoRepositoryAdapterGateway produtoAdapterGateway(SpringProdutoRepository springProdutoRepository) {
        return new ProdutoRepositoryAdapterGateway(springProdutoRepository);
    }

    @Bean
    IProdutoRestAdapterController produtoAdapterController(IGestaoProdutoUseCase produtoUseCase) {
        return new ProdutoRestAdapterController(produtoUseCase);
    }
}
