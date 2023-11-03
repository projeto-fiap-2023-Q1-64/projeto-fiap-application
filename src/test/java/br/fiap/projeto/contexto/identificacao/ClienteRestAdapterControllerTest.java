package br.fiap.projeto.contexto.identificacao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.identificacao.adapter.controller.ClienteRestAdapterController;
import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.response.ClienteResponseDTO;
import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.identificacao.usecase.port.IGestaoClienteUsecase;

public class ClienteRestAdapterControllerTest {

    private IGestaoClienteUsecase gestaoClienteUsecase;
    private ClienteRestAdapterController clienteRestAdapterController;

    @BeforeEach
    void setUp() {
        gestaoClienteUsecase = mock(IGestaoClienteUsecase.class);
        clienteRestAdapterController = new ClienteRestAdapterController(gestaoClienteUsecase);
    }

    @Test
    public void insereClienteComSucesso() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        // Simular a chamada ao caso de uso que insere um cliente
        when(gestaoClienteUsecase.insere(any(Cliente.class))).thenReturn(new Cliente(UUID.randomUUID().toString(), "Nome", "51757125868", "email@example.com"));

        ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO("Nome", "51757125868", "email@example.com");
        ClienteResponseDTO clienteResponseDTO = clienteRestAdapterController.insere(clienteRequestDTO);

        assertNotNull(clienteResponseDTO);
        assertEquals("Nome", clienteResponseDTO.getNome());
    }

    @Test
    public void atualizaClienteComSucesso() throws EntradaInvalidaException,
    EntidadeNaoEncontradaException {
    // Simular a chamada ao caso de uso que atualiza um cliente
    when(gestaoClienteUsecase.edita(any(Cliente.class))).thenReturn(new
    Cliente("codigo", "Nome Atualizado", "51757125868", "email@example.com"));

    ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO("Nome Atualizado", "51757125868", "email@example.com");
    ClienteResponseDTO clienteResponseDTO =
    clienteRestAdapterController.atualiza("codigo", clienteRequestDTO);

    assertNotNull(clienteResponseDTO);
    assertEquals("Nome Atualizado", clienteResponseDTO.getNome());
    }

    @Test
    void removeClienteComSucesso() throws EntidadeNaoEncontradaException,
            EntradaInvalidaException {
        // Simular a chamada ao caso de uso que remove um cliente
        assertDoesNotThrow(() -> {
            clienteRestAdapterController.remove("codigo");
        });
    }

    @Test
    void buscaClienteComSucesso() throws EntradaInvalidaException,
    EntidadeNaoEncontradaException {
    // Simular a chamada ao caso de uso que busca um cliente
    when(gestaoClienteUsecase.busca(any(String.class))).thenReturn(new
    Cliente("codigo", "Nome", "51757125868", "email@example.com"));

    ClienteResponseDTO clienteResponseDTO =
    clienteRestAdapterController.busca("codigo");

    assertNotNull(clienteResponseDTO);
    assertEquals("Nome", clienteResponseDTO.getNome());
    }

    @Test
    void buscaTodosClientesComSucesso() throws EntradaInvalidaException {

        // Massa de clientes
        List<Cliente> clientes = Arrays.asList(new Cliente("codigo1", "Nome1", "51757125868", "email1@example.com"),
                new Cliente("codigo2", "Nome2", "21618131885", "email2@example.com"));
        // Simular a chamada ao caso de uso que busca todos os clientes
        when(gestaoClienteUsecase.buscaTodos())
                .thenReturn(clientes);

        List<ClienteResponseDTO> clientesDTO = clienteRestAdapterController.buscaTodos();

        assertNotNull(clientes);
        assertEquals(2, clientes.size());
    }

    @Test
    void buscaPorCpfComSucesso() throws EntidadeNaoEncontradaException, EntradaInvalidaException {
    // Simular a chamada ao caso de uso que busca um cliente por CPF
    when(gestaoClienteUsecase.buscaPorCpf(any(String.class))).thenReturn(new
    Cliente("codigo", "Nome", "51757125868", "email@example.com"));

    ClienteResponseDTO clienteResponseDTO =
    clienteRestAdapterController.buscaPorCpf("51757125868");

    assertNotNull(clienteResponseDTO);
    assertEquals("Nome", clienteResponseDTO.getNome());
    }
}
