package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaPorComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.BuscaPorComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.portGateway.IBuscaPorComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.BuscaComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryUseCase;

@Configuration
public class BuscaPorComandaBeanConfigurationExternal {

    @Bean
    IBuscaPorComandaUseCase buscaComandaUseCase(
            IBuscarComandaRepositoryUseCase buscarComandaRepositoryUseCase) throws ExceptionMessage, Exception {
        return new BuscaComandaUseCase(buscarComandaRepositoryUseCase);
    }

    @Bean
    IBuscaPorComandaControllerAdapter buscaPorComandaControllerAdapter(
            IBuscaPorComandaUseCase buscaPorComandaUseCase) throws ExceptionMessage, Exception {
        return new BuscaPorComandaControllerAdapter(buscaPorComandaUseCase);
    }

    @Bean
    IBuscaPorComandaGatewayAdapter buscaPorUmaComandaGatewayAdapter(
            SpringComandaRepository springComandaRepository) throws ExceptionMessage,
            Exception {
        return new BuscaPorComandaGatewayAdapter(springComandaRepository);
    }
}
