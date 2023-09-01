package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.IntegracaoPedidoException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaRepositoryUseCase;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

public class FinalizarComandaUseCase implements IAtualizarComandaUseCase {

    private final IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase;
    private final ComandaPedidoIntegration comandaPedidoIntegration;
    private final IAtualizarComandaRepositoryUseCase finalizaComandaRepositoryUseCase;

    public FinalizarComandaUseCase(
            IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration,
            IAtualizarComandaRepositoryUseCase finalizaComandaRepositoryUseCase) {
        this.buscarComandaRepositoryUseCase = buscarComandaRepositoryUseCase;
        this.comandaPedidoIntegration = comandaPedidoIntegration;
        this.finalizaComandaRepositoryUseCase = finalizaComandaRepositoryUseCase;
    }

    @Override
    public Comanda alterarStatus(UUID codigoComanda) throws EntradaInvalidaException, IntegracaoPedidoException {
        Comanda comanda = this.buscar(codigoComanda);
        if (!comanda.getStatus().equals(StatusComanda.EM_PREPARACAO)) {
            throw new EntradaInvalidaException(
                    "Status da comanda inválido para esta operação! Precisa estar em EM_PREPARACAO.");
        }

        comanda.atualizaStatus(StatusComanda.FINALIZADO);
        String codigo = enviarStatusPedido(comanda.getCodigoPedido());
        if (codigo.equals(null)) {
            throw new EntradaInvalidaException(comanda.getStatus().toString());
        }

        return finalizaComandaRepositoryUseCase.atualizar(comanda);
    }

    private Comanda buscar(UUID codigoComanda) throws EntradaInvalidaException {
        Optional<Comanda> comanda = buscarComandaRepositoryUseCase.buscar(codigoComanda);
        comanda.orElseThrow(() -> new EntityNotFoundException("Comanda não encontrada!"));
        return comanda.get();
    }

    private String enviarStatusPedido(UUID codigoPedido) throws IntegracaoPedidoException {
        try {
            return comandaPedidoIntegration.prontificar(codigoPedido.toString()).getBody();
        } catch (Exception e) {
            throw new IntegracaoPedidoException("Erro ao tentar informar status pronto ao pedido!");
        }
    }

}
