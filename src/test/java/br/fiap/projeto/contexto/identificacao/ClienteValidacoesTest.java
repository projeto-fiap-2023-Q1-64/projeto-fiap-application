package br.fiap.projeto.contexto.identificacao;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.domain.vo.Email;
import br.fiap.projeto.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteValidacoesTest {

    @Test
    public void testeCpfInvalido() {

        assertThrows(
                InvalidInputException.class,
                () -> new Cliente(UUID.randomUUID(), "nome1", Cpf.fromString("123"), Email.fromString("teste@teste.com")),
                Cpf.CPF_INVALIDO
        );
    }

    @Test
    public void testeCnpjInvalido() {

        assertThrows(
                InvalidInputException.class,
                () -> new Cliente(UUID.randomUUID(), "nome1", Cpf.fromString("01234567890"), Email.fromString("teste")),
                Email.EMAIL_INVALIDO
        );
    }
}
