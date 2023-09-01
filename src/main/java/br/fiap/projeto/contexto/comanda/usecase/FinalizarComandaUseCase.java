package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
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
    public Comanda atualizar(UUID codigoComanda) throws ExceptionMessage {

        Comanda comanda = this.buscar(codigoComanda);
        if (comanda.getStatus().equals(StatusComanda.EM_PREPARACAO)) {
            comanda.atualizaStatus(StatusComanda.FINALIZADO);
            UUID codigo = enviarStatusPedido(comanda.getCodigoPedido());
            if (codigo == null) {
                throw new ExceptionMessage(comanda.getStatus().toString());
            }

        }
        return criarComandaRepositoryUseCase.criar(comanda);
    }

    private Comanda buscar(UUID codigoComanda) throws ExceptionMessage {
        Comanda comanda = buscarComandaRepositoryUseCase
                .buscar(codigoComanda);
        if (comanda.getCodigoComanda() == null) {
            new EntityNotFoundException("Comanda não encontrada!");
        }
        return comanda;
    }

    private UUID enviarStatusPedido(UUID codigoPedido) {
        return comandaPedidoIntegration.prontificar(codigoPedido.toString()).getBody();
    }

}
