package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.ItemPedido;
import br.fiap.projeto.contexto.pedido.entity.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.OperacaoProduto;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidOperacaoProdutoException;
import br.fiap.projeto.contexto.pedido.usecase.exception.ItemNotFoundException;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoRepositoryAdapterGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class PedidoManagementUseCase extends AbstractPedidoUseCase implements IPedidoManagementUseCase {
    public PedidoManagementUseCase(IPedidoRepositoryAdapterGateway IPedidoRepositoryAdapterGateway) {
        super(IPedidoRepositoryAdapterGateway);
    }
    @Override
    public PedidoDTO criaPedido(PedidoCriarDTO pedidoDTO) {
        return IPedidoRepositoryAdapterGateway.salvar(new Pedido(pedidoDTO)).toPedidoDTO();
    }
    @Override
    public PedidoDTO adicionarProduto(UUID codigoPedido, ProdutoPedidoDTO produtoDTO) throws InvalidOperacaoProdutoException {
        Pedido pedido = this.buscar(codigoPedido);
        // TODO - Implementar busca correta do Produto
        ItemPedido itemPedido = new ItemPedido(
                new ItemPedidoCodigo(codigoPedido,
                    produtoDTO.getCodigo()),
                    pedido,
                    1,
                    produtoDTO.getNome(),
                    produtoDTO.getDescricao(),
                    produtoDTO.getPreco(),
                    produtoDTO.getCategoria(),
                    produtoDTO.getImagem(),
                    produtoDTO.getTempoPreparoMin());
        pedido.adicionarItem(itemPedido);
        this.atualizaValorTotal(pedido, itemPedido, OperacaoProduto.ADICIONAR);
        return IPedidoRepositoryAdapterGateway.salvar(pedido).toPedidoDTO();
    }
    @Transactional
    @Override
    public void removerProduto(UUID codigoPedido, UUID codigoProduto) throws InvalidOperacaoProdutoException {
        Pedido pedido = this.buscar(codigoPedido);
        ItemPedido itemPedido = this.getItemPedidoByProduto(codigoProduto,pedido.getItens());
        this.atualizaValorTotal(pedido, itemPedido, OperacaoProduto.REMOVER);
        pedido.getItens().remove(itemPedido);
        IPedidoRepositoryAdapterGateway.salvar(pedido);
    }
    @Override
    public PedidoDTO aumentarQuantidade(UUID codigoPedido, UUID codigoProduto) throws ItemNotFoundException, InvalidOperacaoProdutoException {
        Pedido pedido = this.buscar(codigoPedido);
        ItemPedido itemPedido = this.getItemPedidoByProduto(codigoProduto,pedido.getItens());
        if(itemPedido == null){
            throw new ItemNotFoundException("Item não encontrado na lista");
        }
        itemPedido.adicionarQuantidade();
        this.atualizaValorTotal(pedido, itemPedido, OperacaoProduto.ADICIONAR);
        return IPedidoRepositoryAdapterGateway.salvar(pedido).toPedidoDTO();
    }
    @Transactional
    @Override
    public PedidoDTO reduzirQuantidade(UUID codigoPedido, UUID codigoProduto) throws ItemNotFoundException, InvalidOperacaoProdutoException {
        Pedido pedido = this.buscar(codigoPedido);
        ItemPedido itemPedido = this.getItemPedidoByProduto(codigoProduto,pedido.getItens());
        if(itemPedido == null){
            throw new ItemNotFoundException("Item não encontrado na lista");
        }else{
            this.atualizaValorTotal(pedido, itemPedido, OperacaoProduto.SUBTRAIR);
            if(itemPedido.getQuantidade() <= 1){
                this.removerProduto(codigoPedido, codigoProduto);
            } else {
                itemPedido.reduzirQuantidade();
                return IPedidoRepositoryAdapterGateway.salvar(pedido).toPedidoDTO();
            }
        }
        return null;
    }
    private void atualizaValorTotal(Pedido pedido, ItemPedido itemPedido, OperacaoProduto operacao) throws InvalidOperacaoProdutoException {
        Double valor = pedido.getValorTotal();
        switch (operacao){
            case REMOVER:
                valor -= ( itemPedido.getValorUnitario() * itemPedido.getQuantidade() );
                break;
            case SUBTRAIR:
                valor -= itemPedido.getValorUnitario();
                break;
            case ADICIONAR:
                valor += itemPedido.getValorUnitario();
                break;
            default:
                throw new InvalidOperacaoProdutoException("Operação inválida!");
        }
        pedido.atualizarValorTotal(valor);
    }
    private ItemPedido getItemPedidoByProduto(UUID codigoProduto, List<ItemPedido> itemPedidos){
        return itemPedidos.stream()
                .filter(itemPedido -> itemPedido.getCodigo().getProdutoCodigo().equals(codigoProduto))
                .findFirst()
                .orElse(null);
    }
}
