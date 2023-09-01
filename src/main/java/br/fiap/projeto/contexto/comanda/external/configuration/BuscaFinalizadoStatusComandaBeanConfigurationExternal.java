package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaStatusFinalizadoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.BuscaStatusFinalizadoComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.BuscaFinalizadoStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusFinalizarComandaRepositoryUseCase;

@Configuration
public class BuscaFinalizadoStatusComandaBeanConfigurationExternal {

        @Bean
        IBuscaPorStatusComandaUseCase buscaFinalizadoStatusComandaUseCase(
                        IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase)
                        throws ExceptionMessage, Exception {
                return new BuscaFinalizadoStatusComandaUseCase(
                                buscarPorStatusComandaRepositoryUseCase);
        }

        @Bean
        IBuscaPorStatusComandaControllerAdapter buscaStatusFinalizadoComandaControlleAdapter(
                        IBuscaPorStatusComandaUseCase buscarPorStatusUseCase)
                        throws ExceptionMessage, Exception {
                return new BuscaStatusFinalizadoComandaControllerAdapter(
                                buscarPorStatusUseCase);
        }

        @Bean
        IBuscarPorStatusFinalizarComandaRepositoryUseCase buscaPorStatusFinalizadoComandaGatewayAdapter(
                        SpringComandaRepository springComandaRepository)
                        throws ExceptionMessage, Exception {
                return new BuscaStatusFinalizadoComandaGatewayAdapter(
                                springComandaRepository);
        }
}
