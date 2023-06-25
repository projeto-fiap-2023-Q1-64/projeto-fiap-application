package br.fiap.projeto.contexto.identificacao;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.domain.vo.Email;
import br.fiap.projeto.exception.EntradaInvalidaException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteValidacoesTest {

    @Test
    public void testeCpfInvalido() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID(), "nome1", Cpf.fromString("123"), Email.fromString("teste@teste.com")),
                Cpf.CPF_INVALIDO
        );
    }

    @Test
    public void testeCnpjInvalido() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID(), "nome1", Cpf.fromString("01234567890"), Email.fromString("teste")),
                Email.EMAIL_INVALIDO
        );
    }

    public void testeCodigoAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(null, "nome1", Cpf.fromString("01234567890"), Email.fromString("teste@teste.com")),
                Cliente.CODIGO_AUSENTE
        );
    }

    @Test
    public void testeNomeAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID(), null, Cpf.fromString("01234567890"), Email.fromString("teste@teste.com")),
                Cliente.CPF_AUSENTE
        );
    }

    @Test
    public void testeEmailAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID(), "nome1", Cpf.fromString("01234567890"), null),
                Cliente.EMAIL_AUSENTE
        );
    }

    @Test
    public void testeCpfAusente() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Cliente(UUID.randomUUID(), "nome1", null, Email.fromString("teste@teste.com")),
                Cliente.CPF_AUSENTE
        );
    }
}
