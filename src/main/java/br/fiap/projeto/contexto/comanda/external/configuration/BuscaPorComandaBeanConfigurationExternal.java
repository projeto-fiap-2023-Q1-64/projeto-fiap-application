package br.fiap.projeto.contexto.comanda.external.configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaPorComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.BuscaPorComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.BuscaComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaRepositoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscaPorComandaBeanConfigurationExternal {

    @Bean
    IBuscaPorComandaUseCase buscaComandaUseCase(
            IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase) throws ExceptionMessage, Exception {
        return new BuscaComandaUseCase(buscarComandaRepositoryUseCase);
    }

    @Bean
    IBuscaPorComandaControllerAdapter buscaPorComandaControllerAdapter(
            IBuscaPorComandaUseCase buscaPorComandaUseCase) throws ExceptionMessage, Exception {
        return new BuscaPorComandaControllerAdapter(buscaPorComandaUseCase);
    }

    @Bean
    IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase(
            SpringComandaRepository springComandaRepository) throws ExceptionMessage,
            Exception {
        return new BuscaPorComandaGatewayAdapter(springComandaRepository);
    }
}
