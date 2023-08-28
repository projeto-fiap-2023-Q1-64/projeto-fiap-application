package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.usecase.BuscaFinalizadoStatusComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryPortUseCase;

@Configuration
public class BuscaPreparacaoStatusComandaBeanConfigurationExternal {

    @Bean
    IBuscaPorStatusPortUseCase comandaService(
            IBuscarPorStatusComandaRepositoryPortUseCase buscaPreparacaoComandaRepositoryPortUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration) throws ExceptionMessage, Exception {
        return (IBuscaPorStatusPortUseCase) new BuscaFinalizadoStatusComandaUseCase(
                buscaPreparacaoComandaRepositoryPortUseCase)
                .buscaComandaPorStatus(StatusComanda.EM_PREPARACAO);
    }
}
