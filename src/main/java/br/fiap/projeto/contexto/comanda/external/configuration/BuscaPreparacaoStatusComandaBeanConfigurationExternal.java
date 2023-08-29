package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaStatusPreparacaoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.BuscaStatusPreparacaoComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.portGateway.IBuscaStatusComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.BuscaPreparacaoStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryUseCase;

@Configuration
public class BuscaPreparacaoStatusComandaBeanConfigurationExternal {

        @Bean
        IBuscaPorStatusComandaUseCase buscaPreparacaoStatusComandaUseCase(
                        IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase)
                        throws ExceptionMessage, Exception {
                return new BuscaPreparacaoStatusComandaUseCase(
                                buscarPorStatusComandaRepositoryUseCase);
        }

        @Bean
        IBuscaPorStatusComandaControllerAdapter buscarPorStatusPreparacaoComandaControlleAdapter(
                        IBuscaPorStatusComandaUseCase buscarPorStatusUseCase)
                        throws ExceptionMessage, Exception {
                return new BuscaStatusPreparacaoComandaControllerAdapter(
                                buscarPorStatusUseCase);
        }

        @Bean
        IBuscaStatusComandaGatewayAdapter buscaPorPreparacaoStatusComandaGatewayAdapter(
                        SpringComandaRepository springComandaRepository)
                        throws ExceptionMessage, Exception {
                return new BuscaStatusPreparacaoComandaGatewayAdapter(
                                springComandaRepository);
        }

}
