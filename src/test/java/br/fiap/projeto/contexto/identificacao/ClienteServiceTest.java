package br.fiap.projeto.contexto.identificacao;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import br.fiap.projeto.contexto.identificacao.domain.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.domain.vo.Email;
import br.fiap.projeto.exception.EntityNotFoundException;
import br.fiap.projeto.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

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
        ClienteDTO cliente = new ClienteDTO(UUID.randomUUID(), "TesteBusca", Cpf.fromString(cpf), Email.fromString("teste@busca.com"));
        cliente = clienteService.insere(cliente);

        assertNotNull(cliente);
        assertNotNull(cliente.getCpf());
        assertNotNull(cliente.getCpf());
        assertEquals(cpf, cliente.getCpf().getNumero());

        ClienteDTO clienteBusca = clienteService.buscaPorCpf(cpf);
        assertNotNull(clienteBusca);
        assertNotNull(clienteBusca.getCpf());
        assertNotNull(clienteBusca.getCodigo());
        assertEquals(cpf, clienteBusca.getCpf().getNumero());
    }

    @Test
    public void testeInsercaoValida() {

        ClienteDTO cliente = new ClienteDTO(UUID.randomUUID(), "NomeTeste", Cpf.fromString("98765432109"), Email.fromString("teste@teste.com"));
        ClienteDTO resultado = clienteService.insere(cliente);
        assertNotNull(resultado);
        assertEquals(cliente.getCodigo(), resultado.getCodigo());

        List<ClienteDTO> clienteDTOS = clienteService.buscaTodos();
        assertFalse(CollectionUtils.isEmpty(clienteDTOS));
        assertTrue(clienteDTOS.stream().anyMatch(cli -> cli.getCodigo().equals(cliente.getCodigo())));
    }

    @Test
    public void testeInsercaoInvalida() {

        assertThrows(
                InvalidInputException.class,
                () -> clienteService.insere(new ClienteDTO(UUID.randomUUID(), null, Cpf.fromString("98765432109"), Email.fromString("teste@teste.com"))),
                Cliente.NOME_AUSENTE
        );
        assertThrows(
                InvalidInputException.class,
                () -> clienteService.insere(new ClienteDTO(UUID.randomUUID(), "Nome teste", null, Email.fromString("teste@teste.com"))),
                Cliente.CPF_AUSENTE
        );
        assertThrows(
                InvalidInputException.class,
                () -> clienteService.insere(new ClienteDTO(UUID.randomUUID(), "Nome teste", Cpf.fromString("98765432109"), null)),
                Cliente.EMAIL_AUSENTE
        );

    }

    @Test
    public void testeRemove() {

        UUID codigoEntrada, codigoSaida;
        ClienteDTO cliente, resultado;

        codigoEntrada = UUID.randomUUID();

        cliente = new ClienteDTO(codigoEntrada, "NomeTeste", Cpf.fromString("98765432109"), Email.fromString("teste@teste.com"));
        resultado = clienteService.insere(cliente);
        codigoSaida = resultado.getCodigo();

        cliente = clienteService.busca(codigoSaida);

        assertNotNull(cliente);
        assertEquals(codigoEntrada, codigoSaida);

        clienteService.remove(codigoEntrada);
        assertThrows(
                EntityNotFoundException.class,
                () -> clienteService.busca(codigoEntrada)
        );
        assertThrows(
                EntityNotFoundException.class,
                () -> clienteService.busca(codigoSaida)
        );

    }
}
