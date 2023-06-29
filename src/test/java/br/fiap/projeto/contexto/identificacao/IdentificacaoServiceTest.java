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
public class IdentificacaoServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testeBusca() {

        List<ClienteDTO> clienteDTOS = clienteService.buscaTodos();
        assertFalse(CollectionUtils.isEmpty(clienteDTOS));
    }

    @Test
    public void testeInsere() {

        ClienteDTO cliente = new ClienteDTO(UUID.randomUUID(), "NomeTeste", Cpf.fromString("98765432109"), Email.fromString("teste@teste.com"));
        ClienteDTO resultado = clienteService.insere(cliente);
        assertNotNull(resultado);
        assertEquals(cliente.getCodigo(), resultado.getCodigo());

        List<ClienteDTO> clienteDTOS = clienteService.buscaTodos();
        assertFalse(CollectionUtils.isEmpty(clienteDTOS));
        assertTrue(clienteDTOS.stream().anyMatch(cli -> cli.getCodigo().equals(cliente.getCodigo())));
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