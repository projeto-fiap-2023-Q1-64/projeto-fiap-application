package br.fiap.projeto.contexto.comanda.external.configuration;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.produto.entity.Produto;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.usecase.port.IProdutoRepositoryAdapterGateway;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
public class PostgresComandaDataLoader {

    @Autowired
    private ICriarComandaRepositoryUseCase comandaRepositoryUseCase;

    @PostConstruct
    @SneakyThrows
    public void init() {
        List<Comanda> list = Arrays.asList(
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.FINALIZADO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.FINALIZADO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.FINALIZADO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO)

        );
        list.stream().forEach(c -> {
            try {
                comandaRepositoryUseCase.criar(c);
            } catch (ExceptionMessage e) {
                throw new RuntimeException(e);
            }
        });
    }
}

