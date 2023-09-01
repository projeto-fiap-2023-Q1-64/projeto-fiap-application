package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaStatusPreparacaoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.BuscaStatusPreparacaoComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.BuscaPreparacaoStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusPrepararComandaRepositoryUseCase;

@Configuration
public class BuscaPreparacaoStatusComandaBeanConfigurationExternal {

        @Bean
        IBuscaPorStatusComandaUseCase buscaPreparacaoStatusComandaUseCase(
                        IBuscarPorStatusPrepararComandaRepositoryUseCase buscarPorStatusPrepararComandaRepositoryUseCase)
                        throws ExceptionMessage, Exception {
                return new BuscaPreparacaoStatusComandaUseCase(
                                buscarPorStatusPrepararComandaRepositoryUseCase);
        }

        @Bean
        IBuscaPorStatusComandaControllerAdapter buscarPorStatusPreparacaoComandaControlleAdapter(
                        IBuscaPorStatusComandaUseCase buscarPorStatusUseCase)
                        throws ExceptionMessage, Exception {
                return new BuscaStatusPreparacaoComandaControllerAdapter(
                                buscarPorStatusUseCase);
        }

        @Bean
        IBuscarPorStatusPrepararComandaRepositoryUseCase buscaPorStatusPreparacaoComandaGatewayAdapter(
                        SpringComandaRepository springComandaRepository)
                        throws ExceptionMessage, Exception {
                return new BuscaStatusPreparacaoComandaGatewayAdapter(
                                springComandaRepository);
        }

}
