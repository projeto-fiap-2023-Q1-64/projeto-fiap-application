package br.fiap.projeto.contexto.comanda.external.configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaPorStatusFinalizadoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaPorStatusPreparacaoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaPorStatusRecebidoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusFinalizadoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusPreparacaoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusRecebidoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.BuscaPorStatusComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.BuscaFinalizadoStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.BuscaPreparacaoStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.BuscaRecebidoStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusFinalizadoComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusPreparacaoComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusRecebidoComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscaPorStatusComandaBeanConfigurationExternal {

        @Bean
        IBuscaPorStatusPreparacaoComandaUseCase buscaPreparacaoStatusComandaUseCase(
                        IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase)
                        throws EntradaInvalidaException, Exception {
                return new BuscaPreparacaoStatusComandaUseCase(
                                buscarPorStatusComandaRepositoryUseCase);
        }

        @Bean
        IBuscaPorStatusFinalizadoComandaUseCase buscaFinalizadoStatusComandaUseCase(
                IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase)
                throws EntradaInvalidaException, Exception {
                return new BuscaFinalizadoStatusComandaUseCase(
                        buscarPorStatusComandaRepositoryUseCase);
        }

        @Bean
        IBuscaPorStatusRecebidoComandaUseCase buscaRecebidoStatusComandaUseCase(
                IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase)
                throws EntradaInvalidaException, Exception {
                return new BuscaRecebidoStatusComandaUseCase(
                        buscarPorStatusComandaRepositoryUseCase);
        }

        @Bean
        IBuscaPorStatusRecebidoComandaControllerAdapter buscarPorStatusRecebidoComandaControlleAdapter(
                        IBuscaPorStatusRecebidoComandaUseCase buscaPreparacaoStatusComandaUseCase)
                        throws EntradaInvalidaException, Exception {
                return new BuscaPorStatusRecebidoComandaControllerAdapter(
                        buscaPreparacaoStatusComandaUseCase);
        }

        @Bean
        IBuscaPorStatusPreparacaoComandaControllerAdapter buscarPorStatusPreparacaoComandaControlleAdapter(
                IBuscaPorStatusPreparacaoComandaUseCase buscaPreparacaoStatusComandaUseCase)
                throws EntradaInvalidaException, Exception {
                return new BuscaPorStatusPreparacaoComandaControllerAdapter(
                        buscaPreparacaoStatusComandaUseCase);
        }

        @Bean
        IBuscaPorStatusFinalizadoComandaControllerAdapter buscarPorStatusFinalizadoComandaControlleAdapter(
                IBuscaPorStatusFinalizadoComandaUseCase buscaFinalizadoStatusComandaUseCase)
                throws EntradaInvalidaException, Exception {
                return new BuscaPorStatusFinalizadoComandaControllerAdapter(
                        buscaFinalizadoStatusComandaUseCase);
        }

        @Bean
        IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase(
                        SpringComandaRepository springComandaRepository)
                        throws EntradaInvalidaException, Exception {
                return new BuscaPorStatusComandaGatewayAdapter(
                                springComandaRepository);
        }

}
