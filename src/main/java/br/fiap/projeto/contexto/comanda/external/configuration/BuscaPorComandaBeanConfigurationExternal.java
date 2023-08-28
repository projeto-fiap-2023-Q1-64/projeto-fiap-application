package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryPortUseCase;

@Configuration
public class BuscaPorComandaBeanConfigurationExternal {

    @Bean
    IBuscaPorComandaPortUseCase comandaService(
            IBuscarComandaRepositoryPortUseCase buscaPorComandaRepositoryPortUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration) throws ExceptionMessage, Exception {
        // return new BuscaComandaUseCase(buscaPorComandaRepositoryPortUseCase);
        return null;
    }
}
