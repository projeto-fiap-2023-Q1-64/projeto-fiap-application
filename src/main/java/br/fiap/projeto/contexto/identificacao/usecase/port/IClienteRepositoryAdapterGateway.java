package br.fiap.projeto.contexto.identificacao.usecase.port;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;

import java.util.List;

public interface IClienteRepositoryAdapterGateway {

    Cliente busca(String codigo);

    List<Cliente> buscaTodos();

    Cliente insere(Cliente cliente);

    Cliente atualiza(Cliente cliente);

    void remove(String codigo);

    Cliente buscaPorCpf(String cpf);

    Cliente buscaPorEmail(String email);
}
