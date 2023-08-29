package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.adapter.controller.BuscaStatusRecebidoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.BuscaStatusRecebidoComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.adapter.gateway.portGateway.IBuscaStatusComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.BuscaRecebidoStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryUseCase;

@Configuration
public class BuscaRecebidoStatusComandaBeanConfigurationExternal {

        @Bean
        IBuscaPorStatusComandaUseCase buscaRecebidoStatusComandaUseCase(
                        IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase)
                        throws ExceptionMessage, Exception {
                return new BuscaRecebidoStatusComandaUseCase(
                                buscarPorStatusComandaRepositoryUseCase);
        }

        @Bean
        IBuscaPorStatusComandaControllerAdapter buscaStatusRecebidoComandaControlleAdapter(
                        IBuscaPorStatusComandaUseCase buscarPorStatusUseCase)
                        throws ExceptionMessage, Exception {
                return new BuscaStatusRecebidoComandaControllerAdapter(
                                buscarPorStatusUseCase);
        }

        @Bean
        IBuscaStatusComandaGatewayAdapter buscaPorRecebidoStatusComandaGatewayAdapter(
                        SpringComandaRepository springComandaRepository)
                        throws ExceptionMessage, Exception {
                return new BuscaStatusRecebidoComandaGatewayAdapter(
                                springComandaRepository);
        }
}
