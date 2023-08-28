package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.usecase.FinalizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryPortUseCase;

@Configuration
public class FinalizarComandaBeanConfigurationExternal {

    @Bean
    IAtualizarComandaPortUseCase comandaService(
            IBuscarComandaRepositoryPortUseCase buscarComandaRepositoryPortUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration,
            ICriarComandaRepositoryPortUseCase criarComandaRepositoryPortUseCase) {
        return new FinalizarComandaUseCase(buscarComandaRepositoryPortUseCase, comandaPedidoIntegration,
                criarComandaRepositoryPortUseCase);
    }
}
