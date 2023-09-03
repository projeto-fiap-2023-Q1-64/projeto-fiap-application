package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.PreparaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.PreparaComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.PrepararComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;

@Configuration
public class PrepararComandaBeanConfigurationExternal {

    @Bean
    IAtualizarComandaUseCase prepararComandaUseCase(
            IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase,
            IAtualizarComandaRepositoryUseCase preparaComandaGatewayAdapter) {
        return new PrepararComandaUseCase(buscarPorComandaPorCodigoPedidoRepositoryUseCase,
                preparaComandaGatewayAdapter);
    }

    @Bean
    IAtualizaComandaControllerAdapter preparaComandaControllerAdapter(
            IAtualizarComandaUseCase prepararComandaUseCase) {
        return new PreparaComandaControllerAdapter(prepararComandaUseCase);
    }

    @Bean
    IAtualizarComandaRepositoryUseCase preparaComandaGatewayAdapter(
            SpringComandaRepository springComandaRepository) {
        return new PreparaComandaGatewayAdapter(springComandaRepository);
    }
}
