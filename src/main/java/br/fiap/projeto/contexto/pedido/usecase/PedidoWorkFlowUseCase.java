package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoWorkFlowUseCase;

import java.util.UUID;

public class PedidoWorkFlowUseCase extends AbstractPedidoUseCase  implements IPedidoWorkFlowUseCase {
    public PedidoWorkFlowUseCase(IPedidoRepositoryAdapterGateway IPedidoRepositoryAdapterGateway) {
        super(IPedidoRepositoryAdapterGateway);
    }
    public Pedido receber(UUID codigo) throws Exception {
        Pedido pedido = this.buscar(codigo);
        if(pedido.getStatus().equals(StatusPedido.INICIADO)){
            if(pedido.getItens().isEmpty()){
                throw new NoItensException(codigo.toString());
            }else {
                pedido.atualizarStatus(StatusPedido.RECEBIDO);
            }
        }else{
            throw new InvalidStatusException("Status inválido!");
        }
        return IPedidoRepositoryAdapterGateway.salvar(pedido);
    }
    @Override
    public Pedido pagar(UUID codigo) throws Exception {
        Pedido pedido = this.buscar(codigo);
        if(pedido.getStatus().equals(StatusPedido.RECEBIDO)){
            pedido.atualizarStatus(StatusPedido.PAGO);
        }else{
            throw new InvalidStatusException("Status inválido!");
        }
        return IPedidoRepositoryAdapterGateway.salvar(pedido);
    }
    @Override
    public Pedido preparar(UUID codigo) throws Exception {
        Pedido pedido = this.buscar(codigo);
        if(pedido.getStatus().equals(StatusPedido.PAGO)){
            pedido.atualizarStatus(StatusPedido.EM_PREPARACAO);
        }else{
            throw new InvalidStatusException("Status inválido!");
        }
        return IPedidoRepositoryAdapterGateway.salvar(pedido);
    }
    @Override
    public Pedido prontificar(UUID codigo) throws Exception {
        Pedido pedido = this.buscar(codigo);
        if(pedido.getStatus().equals(StatusPedido.EM_PREPARACAO)){
            pedido.atualizarStatus(StatusPedido.PRONTO);
        }else{
            throw new InvalidStatusException("Status inválido!");
        }
        return IPedidoRepositoryAdapterGateway.salvar(pedido);
    }
    @Override
    public Pedido finalizar(UUID codigo) throws Exception {
        Pedido pedido = this.buscar(codigo);
        if(pedido.getStatus().equals(StatusPedido.PRONTO)){
            pedido.atualizarStatus(StatusPedido.FINALIZADO);
        }else{
            throw new InvalidStatusException("Status inválido!");
        }
        return IPedidoRepositoryAdapterGateway.salvar(pedido);
    }
}
