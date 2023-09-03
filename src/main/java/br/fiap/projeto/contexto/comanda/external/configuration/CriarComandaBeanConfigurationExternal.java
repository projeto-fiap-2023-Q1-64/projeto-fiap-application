package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.CriaComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.ICriarComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.CriaComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.CriarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.ICriarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.ICriarComandaRepositoryUseCase;

@Configuration
public class CriarComandaBeanConfigurationExternal {

    @Bean
    ICriarComandaUseCase criarComandaUseCase(
            ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase,
            IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarPorComandaPorCodigoPedidoRepositoryUseCase) {
        return new CriarComandaUseCase(criarComandaRepositoryUseCase, buscarPorComandaPorCodigoPedidoRepositoryUseCase);
    }

    @Bean
    ICriarComandaControllerAdapter criarComandaControllerAdapter(
            ICriarComandaUseCase criarComandaUseCase) {
        return new CriaComandaControllerAdapter(criarComandaUseCase);
    }

    @Bean
    ICriarComandaRepositoryUseCase criarComandaGatewayAdapter(
            SpringComandaRepository springComandaRepository) {
        return new CriaComandaGatewayAdapter(springComandaRepository);
    }
}
