package br.fiap.projeto.contexto.identificacao.usecase.port;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteRepositoryAdapterGateway {

    Optional<Cliente> busca(String codigo);

    List<Cliente> buscaTodos();

    Cliente insere(Cliente cliente);

    Cliente atualiza(Cliente cliente);

    void remove(Cliente cliente);

    Optional<Cliente> buscaPorCpf(String cpf);

    Optional<Cliente> buscaPorEmail(String email);

    Optional<Cliente> buscaPorCodigoEDataExclusaoNula(String codigo);
}
