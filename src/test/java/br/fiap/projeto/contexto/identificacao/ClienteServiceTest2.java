package br.fiap.projeto.contexto.identificacao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.usecase.GestaoClienteUseCase;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;

class ClienteServiceTest2 {

    private IClienteRepositoryAdapterGateway clienteRepositoryAdapterGateway;
    private GestaoClienteUseCase gestaoClienteUseCase;

    @BeforeEach
    void setUp() {
        clienteRepositoryAdapterGateway = mock(IClienteRepositoryAdapterGateway.class);
        gestaoClienteUseCase = new GestaoClienteUseCase(clienteRepositoryAdapterGateway);
    }

    @Test
    void insereClienteComCpfEDuplicado() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
      
        when(clienteRepositoryAdapterGateway.buscaPorCpf(anyString())).thenReturn(Optional.of(new Cliente("codigo1", "Nome", "21618131885", "email@email.com.br")));

        Cliente clienteParaInserir = new Cliente("codigo2", "Novo Nome", "51757125868", "novoemail@email.com.br");

        assertThrows(EntradaInvalidaException.class, () -> {
            gestaoClienteUseCase.insere(clienteParaInserir);
        });
    }

    @Test
    void insereClienteComEmailDuplicado() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        // Simular o cenário onde o cliente com o mesmo e-mail já existe
        when(clienteRepositoryAdapterGateway.buscaPorCpf(anyString())).thenReturn(Optional.empty());
        when(clienteRepositoryAdapterGateway.buscaPorEmail(anyString())).thenReturn(Optional.of(new Cliente("codigo1", "Nome", "21618131885", "email@email.com")));

        Cliente clienteParaInserir = new Cliente("codigo2", "Novo Nome", "51757125868", "novoemail@email.com.br");

        assertThrows(EntradaInvalidaException.class, () -> {
            gestaoClienteUseCase.insere(clienteParaInserir);
        });
    }

    @Test
    void insereClienteComSucesso() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        // Simular o cenário onde o cliente não existe previamente
        when(clienteRepositoryAdapterGateway.buscaPorCpf(anyString())).thenReturn(Optional.empty());
        when(clienteRepositoryAdapterGateway.buscaPorEmail(anyString())).thenReturn(Optional.empty());
        when(clienteRepositoryAdapterGateway.insere(any(Cliente.class))).thenReturn(new Cliente("codigo2", "Novo Nome", "51757125868", "novoemail@email.com.br"));

        Cliente clienteParaInserir = new Cliente("codigo2", "Novo Nome", "51757125868", "novoemail@email.com.br");

        Cliente clienteInserido = gestaoClienteUseCase.insere(clienteParaInserir);
        assertNotNull(clienteInserido);
    }

    @Test
    void editaClienteComCodigoAusente() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        // Simular o cenário onde o código do cliente está ausente

        assertThrows(EntradaInvalidaException.class, () -> {
            gestaoClienteUseCase.edita(new Cliente(null, "Novo Nome", "51757125868", "novoemail@email.com.br"));
        });
    }

    @Test
    void editaClienteInexistente() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        // Simular o cenário onde o cliente não existe
        when(clienteRepositoryAdapterGateway.busca(anyString())).thenReturn(Optional.empty());

        Cliente clienteParaEditar = new Cliente("1234", "Novo Nome", "51757125868", "novoemail@email.com.br");

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            gestaoClienteUseCase.edita(clienteParaEditar);
        });
    }

    @Test
    void editaClienteComSucesso() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        // Simular o cenário onde o cliente existe
        when(clienteRepositoryAdapterGateway.busca(anyString())).thenReturn(Optional.of(new Cliente("123", "Novo Nome", "51757125868", "novoemail@email.com.br")));
        when(clienteRepositoryAdapterGateway.atualiza(any(Cliente.class))).thenReturn(new Cliente("123", "Novo Nome2", "51757125868", "novoemail@email.com.br"));

        Cliente clienteParaEditar = new Cliente("123", "Novo Nome", "51757125868", "novoemail@email.com.br");

        Cliente clienteEditado = gestaoClienteUseCase.edita(clienteParaEditar);
        assertEquals("Novo Nome2", clienteEditado.getNome());
    }

    @Test
    void removeClienteComCodigoAusente() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        // Simular o cenário onde o código do cliente está ausente
        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            gestaoClienteUseCase.remove(null);
        });
    }

    @Test
    void removeClienteInexistente() throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        // Simular o cenário onde o cliente não existe
        when(clienteRepositoryAdapterGateway.buscaPorCodigoEDataExclusaoNula(anyString())).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            gestaoClienteUseCase.remove("codigo");
        });
    }

    @Test
    void removeClienteComSucesso() throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        // Simular o cenário onde o cliente existe
        when(clienteRepositoryAdapterGateway.buscaPorCodigoEDataExclusaoNula(anyString())).thenReturn(Optional.of(new Cliente("123", "Novo Nome", "51757125868", "novoemail@email.com.br")));

        assertDoesNotThrow(() -> {
            gestaoClienteUseCase.remove("codigo");
        });
    }

    @Test
    void buscaClienteComCodigoAusente() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        // Simular o cenário onde o código do cliente está ausente
        assertThrows(EntradaInvalidaException.class, () -> {
            gestaoClienteUseCase.busca(null);
        });
    }

    @Test
    void buscaClienteInexistente() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        // Simular o cenário onde o cliente não existe
        when(clienteRepositoryAdapterGateway.busca(anyString())).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            gestaoClienteUseCase.busca("codigo");
        });
    }

    @Test
    void buscaClienteComSucesso() throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        // Simular o cenário onde o cliente existe
        when(clienteRepositoryAdapterGateway.busca(anyString())).thenReturn(Optional.of(new Cliente("123", "Novo Nome", "51757125868", "novoemail@email.com.br")));

        Cliente cliente = gestaoClienteUseCase.busca("codigo");
        assertNotNull(cliente);
   

}
}
