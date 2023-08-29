package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.FinalizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.FinalizaComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.FinalizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;

@Configuration
public class FinalizarComandaBeanConfigurationExternal {

    @Bean
    IAtualizarComandaUseCase finalizarComandaUseCase(
            IBuscarComandaRepositoryUseCase buscarComandaRepositoryUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration,
            ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase) {
        return new FinalizarComandaUseCase(buscarComandaRepositoryUseCase, comandaPedidoIntegration,
                criarComandaRepositoryUseCase);
    }

    @Bean
    IAtualizaComandaControllerAdapter finalizaComandaControllerAdapter(
            IAtualizarComandaUseCase atualizarComandaUseCase) {
        return new FinalizaComandaControllerAdapter(atualizarComandaUseCase);
    }

    @Bean
    IAtualizarComandaRepositoryUseCase finalizaComandaGatewayAdapter(
            SpringComandaRepository springComandaRepository) {
        return new FinalizaComandaGatewayAdapter(springComandaRepository);
    }
}
