package br.fiap.projeto.contexto.comanda.infrastructure.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.contexto.comanda.infrastructure.repository.postgres.PostgresComandaRepository;

@Configuration
public class PostgresComandaDataLoader {

    @Autowired
    private PostgresComandaRepository ComandaRepository;

    @PostConstruct
    public void init() {
        // List<Comanda> list = Arrays.asList(new Comanda(123456, null, 1, 28/06/23));
        // list.stream().forEach(p -> ComandaRepository.criaComanda(p));
    }
}
