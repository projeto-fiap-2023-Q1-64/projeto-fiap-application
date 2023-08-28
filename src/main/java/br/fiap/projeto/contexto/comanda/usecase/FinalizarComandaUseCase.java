package br.fiap.projeto.contexto.comanda.usecase;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryPortUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryPortUseCase;

public class FinalizarComandaUseCase implements IAtualizarComandaPortUseCase {

    private final IBuscarComandaRepositoryPortUseCase buscarComandaRepositoryPortUseCase;
    private final ComandaPedidoIntegration comandaPedidoIntegration;
    private final ICriarComandaRepositoryPortUseCase criarComandaRepositoryPortUseCase;

    public FinalizarComandaUseCase(
            IBuscarComandaRepositoryPortUseCase buscarComandaRepositoryPortUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration,
            ICriarComandaRepositoryPortUseCase criarComandaRepositoryPortUseCase) {
        this.buscarComandaRepositoryPortUseCase = buscarComandaRepositoryPortUseCase;
        this.comandaPedidoIntegration = comandaPedidoIntegration;
        this.criarComandaRepositoryPortUseCase = criarComandaRepositoryPortUseCase;
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
        return criarComandaRepositoryPortUseCase.criar(comanda);
    }

    private Comanda buscar(UUID codigoComanda) throws ExceptionMessage {
        Optional<Comanda> optionalComanda = buscarComandaRepositoryPortUseCase
                .buscar(codigoComanda);
        optionalComanda.orElseThrow(() -> new EntityNotFoundException("Comanda não encontrada!"));
        return optionalComanda.get();
    }

    private UUID enviarStatusPedido(UUID codigoPedido) {
        return comandaPedidoIntegration.prontificar(codigoPedido.toString()).getBody();
    }

}
