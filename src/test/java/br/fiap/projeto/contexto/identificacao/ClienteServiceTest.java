package br.fiap.projeto.contexto.identificacao;

import br.fiap.projeto.contexto.identificacao.application.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.EntidadeNaoEncontradaException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.CollectionUtils;

import java.util.List;
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
        ClienteRequestDTO request = new ClienteRequestDTO("TesteBusca", cpf, "teste@busca.com");
        ClienteDTO cliente;
        cliente = clienteService.insere(request);

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

        ClienteRequestDTO cliente = new ClienteRequestDTO("NomeTeste", "98765432109", "teste@teste.com");
        ClienteDTO resultado = clienteService.insere(cliente);
        assertNotNull(resultado);

        List<ClienteDTO> clienteDTOS = clienteService.buscaTodos();
        assertFalse(CollectionUtils.isEmpty(clienteDTOS));
    }

    @Test
    public void testeRemove() {

        String codigoSaida;
        AtomicReference<ClienteRequestDTO> cliente;
        AtomicReference<ClienteDTO> resultado;

        cliente = new AtomicReference<>();
        resultado = new AtomicReference<>();

        cliente.set(new ClienteRequestDTO("NomeTeste", "45678912301", "teste@teste.com"));
        resultado.set(clienteService.insere(cliente.get()));
        codigoSaida = resultado.get().getCodigo();

        resultado.set(clienteService.busca(codigoSaida));

        assertNotNull(resultado);

        clienteService.remove(resultado.get().getCodigo());
        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> clienteService.busca(resultado.get().getCodigo())
        );
        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> clienteService.busca(codigoSaida)
        );

    }
}
