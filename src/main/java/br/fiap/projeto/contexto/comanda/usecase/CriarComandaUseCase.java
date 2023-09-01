package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.ICriarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;

import java.util.UUID;

public class CriarComandaUseCase implements ICriarComandaUseCase {

    private final ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase;

    public CriarComandaUseCase(ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase) {
        this.criarComandaRepositoryUseCase = criarComandaRepositoryUseCase;
    }

    @Override
    public Comanda criarComanda(UUID codigoPedido) throws EntradaInvalidaException {
        if(codigoPedido == null){
            throw new EntradaInvalidaException("Código de pedido inválido!");
        }

        //TODO: buscar comanda por código do pedido e validar que não existe, caso exista dar um erro

        return criarComandaRepositoryUseCase.criar(new Comanda(UUID.randomUUID(),
                codigoPedido,
                StatusComanda.RECEBIDO));
    }

    // TODO: utilizar o modelo e criar um gateway que busca comanda por código do pedido para injetar aqui e fazer a consulta para a validação.
//    private Comanda buscar(UUID codigoComanda) throws EntradaInvalidaException {
//        Optional<Comanda> comanda = buscarComandaRepositoryUseCase.buscar(codigoComanda);
//        comanda.orElseThrow(() -> new EntityNotFoundException("Comanda não encontrada!"));
//        return comanda.get();
//    }

}
