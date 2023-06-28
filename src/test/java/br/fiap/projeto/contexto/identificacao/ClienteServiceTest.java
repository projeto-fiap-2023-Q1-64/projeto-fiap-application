package br.fiap.projeto.contexto.identificacao;

import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.EntidadeNaoEncontradaException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testeBusca() {

        List<ClienteDTO> clienteDTOS = clienteService.buscaTodos();
        assertFalse(CollectionUtils.isEmpty(clienteDTOS));
    }

    @Test
    public void testeBuscaPorCpf() {

        String cpf = "09876543210";
        ClienteDTO cliente = new ClienteDTO(UUID.randomUUID().toString(), "TesteBusca", cpf, "teste@busca.com");
        cliente = clienteService.insere(cliente);

        assertNotNull(cliente);
        assertNotNull(cliente.getCpf());
        assertNotNull(cliente.getCpf());
        assertEquals(cpf, cliente.getCpf());

        ClienteDTO clienteBusca = clienteService.buscaPorCpf(cpf);
        assertNotNull(clienteBusca);
        assertNotNull(clienteBusca.getCpf());
        assertNotNull(clienteBusca.getCodigo());
        assertEquals(cpf, clienteBusca.getCpf());
    }

    @Test
    public void testeInsere() {

        ClienteDTO cliente = new ClienteDTO("NomeTeste", "98765432109", "teste@teste.com");
        ClienteDTO resultado = clienteService.insere(cliente);
        assertNotNull(resultado);

        List<ClienteDTO> clienteDTOS = clienteService.buscaTodos();
        assertFalse(CollectionUtils.isEmpty(clienteDTOS));
    }

    @Test
    public void testeRemove() {

        String codigoSaida;
        AtomicReference<ClienteDTO> cliente, resultado;

        cliente = new AtomicReference<>();
        resultado = new AtomicReference<>();

        cliente.set(new ClienteDTO("NomeTeste", "45678912301", "teste@teste.com"));
        resultado.set(clienteService.insere(cliente.get()));
        codigoSaida = resultado.get().getCodigo();

        cliente.set(clienteService.busca(codigoSaida));

        assertNotNull(cliente);

        clienteService.remove(cliente.get().getCodigo());
        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> clienteService.busca(cliente.get().getCodigo())
        );
        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> clienteService.busca(codigoSaida)
        );

    }
}
