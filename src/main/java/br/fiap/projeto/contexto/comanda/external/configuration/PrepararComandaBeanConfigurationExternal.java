package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.usecase.PrepararComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryPortUseCase;

@Configuration
public class PrepararComandaBeanConfigurationExternal {

    @Bean
    IAtualizarComandaPortUseCase comandaService(IBuscarComandaRepositoryPortUseCase buscarComandaRepositoryPortUseCase,
            ICriarComandaRepositoryPortUseCase criarComandaRepositoryPortUseCase) {
        return new PrepararComandaUseCase(buscarComandaRepositoryPortUseCase, criarComandaRepositoryPortUseCase);
    }
}
