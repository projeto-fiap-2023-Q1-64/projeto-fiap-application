package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.adapter.gateway.BuscaPorComandaPorCodigoPedidoGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.BuscaComandaPorCodigoPedidoUseCase;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaPorCodigoPedidoUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;

@Configuration
public class BuscaComandaPorCodigoPedidoBeanConfigurationExternal {

    @Bean
    IBuscaPorComandaPorCodigoPedidoUseCase buscaComandaPorCodigoPedidoUseCase(
            IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarComandaPorCodigoPedidoRepositoryUseCase)
            throws EntradaInvalidaException, Exception {
        return new BuscaComandaPorCodigoPedidoUseCase(buscarComandaPorCodigoPedidoRepositoryUseCase);
    }

    @Bean
    IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarComandaPorCodigoPedidoRepositoryUseCase(
            SpringComandaRepository springComandaRepository) throws EntradaInvalidaException,
            Exception {
        return new BuscaPorComandaPorCodigoPedidoGatewayAdapter(springComandaRepository);
    }
}
