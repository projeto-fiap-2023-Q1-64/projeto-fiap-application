package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

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
    public Comanda finalizar(UUID codigoComanda) throws EntradaInvalidaException {
        Comanda comanda = this.buscar(codigoComanda);
        if (!comanda.getStatus().equals(StatusComanda.EM_PREPARACAO)) {
            throw new EntradaInvalidaException("Status da comanda inválido para esta operação! Precisa estar em EM_PREPARACAO.");
        }

        comanda.atualizaStatus(StatusComanda.FINALIZADO);
        UUID codigo = enviarStatusPedido(comanda.getCodigoPedido());
        if (codigo == null) {
            throw new EntradaInvalidaException(comanda.getStatus().toString());
        }

        return criarComandaRepositoryUseCase.criar(comanda);
    }

    private Comanda buscar(UUID codigoComanda) throws EntradaInvalidaException {
        Comanda comanda = buscarComandaRepositoryUseCase.buscar(codigoComanda);
        if (comanda.getCodigoComanda() == null) {
            throw new EntityNotFoundException("Comanda não encontrada!");
        }
        return comanda;
    }

    private UUID enviarStatusPedido(UUID codigoPedido) {
        return comandaPedidoIntegration.prontificar(codigoPedido.toString()).getBody();
    }

}
