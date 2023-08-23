package br.fiap.projeto.contexto.identificacao.external.config;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Configuration
public class PostgresClienteDataLoader {

    @Autowired
    private IClienteRepositoryAdapterGateway clienteRepository;

    @PostConstruct
    @SneakyThrows
    public void init() {
        List<Cliente> clientes = Collections.singletonList(
                new Cliente(UUID.randomUUID().toString(), "Cliente1", "01234567890", "cliente1@test.com")
        );
        clientes.forEach(cli -> clienteRepository.insere(cli));
    }
}
