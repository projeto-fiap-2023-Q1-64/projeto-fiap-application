package br.fiap.projeto.contexto.identificacao.infrastructure.config;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.port.repository.ClienteRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
public class PostgresClienteDataLoader {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostConstruct
    @SneakyThrows
    public void init() {

        List<Cliente> clientes = Arrays.asList(
            new Cliente(UUID.randomUUID(), "Cliente1", "01234567890", "cliente1@test.com")
        );
        clientes.forEach(cli -> clienteRepository.insere(cli));
    }
}
