package br.fiap.projeto.contexto.comanda.external.configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.FinalizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.FinalizaComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.FinalizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IAtualizarFinalizarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinalizarComandaBeanConfigurationExternal {

    @Bean
    IAtualizarComandaUseCase finalizarComandaUseCase(
            IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration,
            ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase) {
        return new FinalizarComandaUseCase(buscarComandaRepositoryUseCase, comandaPedidoIntegration,
                criarComandaRepositoryUseCase);
    }

    @Bean
    IAtualizaComandaControllerAdapter finalizaComandaControllerAdapter(
            IAtualizarComandaUseCase finalizarComandaUseCase) {
        return new FinalizaComandaControllerAdapter(finalizarComandaUseCase);
    }

    @Bean
    IAtualizarFinalizarComandaRepositoryUseCase finalizaUmaComandaGatewayAdapter(
            SpringComandaRepository springComandaRepository) {
        return new FinalizaComandaGatewayAdapter(springComandaRepository);
    }
}
