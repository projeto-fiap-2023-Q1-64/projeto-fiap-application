package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.PreparaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.PreparaComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.PrepararComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;

@Configuration
public class PrepararComandaBeanConfigurationExternal {

    @Bean
    IAtualizarComandaUseCase prepararComandaUseCase(
            IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase,
            ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase) {
        return new PrepararComandaUseCase(buscarComandaRepositoryUseCase, criarComandaRepositoryUseCase);
    }

    @Bean
    IAtualizaComandaControllerAdapter preparaComandaControllerAdapter(
            IAtualizarComandaUseCase preparaComandaUseCase) {
        return new PreparaComandaControllerAdapter(preparaComandaUseCase);
    }

    @Bean
    IAtualizarComandaRepositoryUseCase preparaUmaComandaGatewayAdapter(
            SpringComandaRepository springComandaRepository) {
        return new PreparaComandaGatewayAdapter(springComandaRepository);
    }
}
