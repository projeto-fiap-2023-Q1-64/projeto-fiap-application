package br.fiap.projeto.contexto.comanda.usecase;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;

public class FinalizarComandaUseCase implements IAtualizarComandaUseCase {

    private final IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase;
    private final ComandaPedidoIntegration comandaPedidoIntegration;
    private final ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase;

    public FinalizarComandaUseCase(
            IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration,
            ICriarComandaRepositoryUseCase criarComandaRepositoryUseCase) {
        this.buscarComandaRepositoryUseCase = buscarComandaRepositoryUseCase;
        this.comandaPedidoIntegration = comandaPedidoIntegration;
        this.criarComandaRepositoryUseCase = criarComandaRepositoryUseCase;
    }

    @Override
    public Comanda alterarStatus(UUID codigoComanda) throws EntradaInvalidaException {
        Comanda comanda = this.buscar(codigoComanda).get();
        if (!comanda.getStatus().equals(StatusComanda.EM_PREPARACAO)) {
            throw new EntradaInvalidaException(
                    "Status da comanda inválido para esta operação! Precisa estar em EM_PREPARACAO.");
        }

        comanda.atualizaStatus(StatusComanda.FINALIZADO);
        String codigo = enviarStatusPedido(comanda.getCodigoPedido());
        if (codigo.equals(null)) {
            throw new EntradaInvalidaException(comanda.getStatus().toString());
        }

        return criarComandaRepositoryUseCase.criar(comanda);
    }

    private Optional<Comanda> buscar(UUID codigoComanda) throws EntradaInvalidaException {
        Optional<Comanda> comanda = buscarComandaRepositoryUseCase.buscar(codigoComanda);
        if (comanda.get() == null) {
            throw new EntityNotFoundException("Comanda não encontrada!");
        }
        return comanda;
    }

    private String enviarStatusPedido(UUID codigoPedido) {
        return comandaPedidoIntegration.prontificar(codigoPedido.toString()).getBody();
    }

}
