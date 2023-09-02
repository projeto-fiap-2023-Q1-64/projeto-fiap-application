package br.fiap.projeto.contexto.comanda.external.configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.FinalizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.FinalizaComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.FinalizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaRepositoryUseCase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinalizarComandaBeanConfigurationExternal {

    @Bean
    IAtualizarComandaUseCase finalizarComandaUseCase(
            IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration,
            IAtualizarComandaRepositoryUseCase finalizaComandaGatewayAdapter) {
        return new FinalizarComandaUseCase(buscarComandaRepositoryUseCase, comandaPedidoIntegration,
                finalizaComandaGatewayAdapter);
    }

    @Bean
    IAtualizaComandaControllerAdapter finalizaComandaControllerAdapter(
            IAtualizarComandaUseCase finalizarComandaUseCase) {
        return new FinalizaComandaControllerAdapter(finalizarComandaUseCase);
    }

    @Bean
    IAtualizarComandaRepositoryUseCase finalizaComandaGatewayAdapter(
            SpringComandaRepository springComandaRepository,
            IBuscarPorComandaRepositoryUseCase buscarPorComandaGatewayAdapter) {
        return new FinalizaComandaGatewayAdapter(springComandaRepository);
    }
}
