package br.fiap.projeto.contexto.identificacao;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.entity.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.entity.vo.Email;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntradaInvalidaException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteValidacoesTest {

    @Test
    public void testeCpfInvalido() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID().toString(), "nome1", "123", "teste@teste.com"),
                Cpf.CPF_INVALIDO);
    }

    @Test
    public void testeEmailInvalido() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID().toString(), "nome1", "01234567890", "teste"),
                Email.EMAIL_INVALIDO);
    }

    @Test
    public void testeCodigoAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(null, "nome1", "01234567890", "teste@teste.com"),
                Cliente.CODIGO_AUSENTE);
    }

    @Test
    public void testeNomeAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID().toString(), null, "01234567890", "teste@teste.com"),
                Cliente.CPF_AUSENTE);
    }

    @Test
    public void testeEmailAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID().toString(), "nome1", "01234567890", null),
                Cliente.EMAIL_AUSENTE);
    }

    @Test
    public void testeCpfAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID().toString(), "nome1", null, "teste@teste.com"),
                Cliente.CPF_AUSENTE);
    }
}
