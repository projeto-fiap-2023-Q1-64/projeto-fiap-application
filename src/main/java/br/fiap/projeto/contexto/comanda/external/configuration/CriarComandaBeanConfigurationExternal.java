package br.fiap.projeto.contexto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.usecase.CriarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.ICriarComandaPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryPortUseCase;

@Configuration
public class CriarComandaBeanConfigurationExternal {

    @Bean
    ICriarComandaPortUseCase comandaService(ICriarComandaRepositoryPortUseCase criarComandaRepositoryPortUseCase) {
        return new CriarComandaUseCase(criarComandaRepositoryPortUseCase);
    }
}
