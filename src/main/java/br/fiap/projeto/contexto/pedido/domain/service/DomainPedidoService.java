package br.fiap.projeto.contexto.pedido.domain.service;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.domain.dto.ItemPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.enums.OperacaoProduto;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.domain.exception.InvalidOperacaoProdutoException;
import br.fiap.projeto.contexto.pedido.domain.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.domain.exception.ItemNotFoundException;
import br.fiap.projeto.contexto.pedido.domain.port.repository.ItemPedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.domain.port.repository.PedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class DomainPedidoService implements PedidoService {
    private final PedidoRepositoryPort pedidoRepositoryPort;
    public DomainPedidoService(PedidoRepositoryPort pedidoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }
    //-------------------------------------------------------------------------//
    //                         BASE CRUD
    //-------------------------------------------------------------------------//
    @Override
    public PedidoDTO criaPedido(PedidoCriarDTO pedidoDTO) {
        return pedidoRepositoryPort.salvar(new Pedido(pedidoDTO)).toPedidoDTO();
    }
    @Override
    public PedidoDTO buscaPedido(UUID codigo) {
        return this.buscar(codigo).toPedidoDTO();
    }
    @Override
    public List<PedidoDTO> buscaTodos() {
        List<Pedido> pedidoLista = pedidoRepositoryPort.buscaTodos();
        return pedidoLista.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    @Override
    public void removePedido(UUID codigo) {
        pedidoRepositoryPort.removePedido(codigo);
    }
    //-------------------------------------------------------------------------//
    //                MÉTODOS DE MANUPULAÇÃO DE ITENS DO PEDIDO
    //-------------------------------------------------------------------------//
    @Override
    public PedidoDTO adicionarProduto(UUID codigo, ProdutoPedidoDTO produtoDTO) throws InvalidOperacaoProdutoException {
        Pedido pedido = this.buscar(codigo);
        ItemPedido itemPedido = new ItemPedido(
                new ItemPedidoCodigo(codigo,
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
        return pedidoRepositoryPort.salvar(pedido).toPedidoDTO();
    }
    @Transactional
    @Override
    public void removerProduto(UUID codigo, UUID produtoCodigo) throws InvalidOperacaoProdutoException {
        Pedido pedido = this.buscar(codigo);
        ItemPedido itemPedido = this.getItemPedidoByProduto(produtoCodigo,pedido.getItens());
        this.atualizaValorTotal(pedido, itemPedido, OperacaoProduto.REMOVER);
        pedido.getItens().remove(itemPedido);
        pedidoRepositoryPort.salvar(pedido);
    }
    @Override
    public PedidoDTO aumentarQuantidade(UUID codigo, ProdutoPedidoDTO produtoDTO) throws ItemNotFoundException, InvalidOperacaoProdutoException {
        Pedido pedido = this.buscar(codigo);
        ItemPedido itemPedido = this.getItemPedidoByProduto(produtoDTO.getCodigo(),pedido.getItens());
        if(itemPedido == null){
            throw new ItemNotFoundException("Item não encontrado na lista");
        }
        itemPedido.adicionarQuantidade();
        this.atualizaValorTotal(pedido, itemPedido, OperacaoProduto.ADICIONAR);

        return pedidoRepositoryPort.salvar(pedido).toPedidoDTO();
    }
    @Override
    public PedidoDTO reduzirQuantidade(UUID codigo, ProdutoPedidoDTO produtoDTO) throws ItemNotFoundException, InvalidOperacaoProdutoException {
        Pedido pedido = this.buscar(codigo);
        ItemPedido itemPedido = this.getItemPedidoByProduto(produtoDTO.getCodigo(),pedido.getItens());
        if(itemPedido == null){
            throw new ItemNotFoundException("Item não encontrado na lista");
        }else{
            if(itemPedido.getQuantidade() <= 1){
                this.removerProduto(codigo, produtoDTO.getCodigo());
            } else {
                itemPedido.reduzirQuantidade();
                this.atualizaValorTotal(pedido, itemPedido, OperacaoProduto.SUBTRAIR);
                return pedidoRepositoryPort.salvar(pedido).toPedidoDTO();
            }
        }
        return null;
    }
    //-------------------------------------------------------------------------//
    //                  RETORNO DOS PEDIDOS POR  STATUS
    //-------------------------------------------------------------------------//
    public List<PedidoDTO> buscarTodosRecebido(){
        List<Pedido> pedidosRecebidos = pedidoRepositoryPort.buscaPedidosPorStatus(StatusPedido.RECEBIDO);
        return pedidosRecebidos.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    public List<PedidoDTO> buscarTodosEmPreparacao(){
        List<Pedido> pedidosRecebidos = pedidoRepositoryPort.buscaPedidosPorStatus(StatusPedido.EM_PREPARACAO);
        return pedidosRecebidos.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    public List<PedidoDTO> buscarTodosPronto(){
        List<Pedido> pedidosRecebidos = pedidoRepositoryPort.buscaPedidosPorStatus(StatusPedido.PRONTO);
        return pedidosRecebidos.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    public List<PedidoDTO> buscarTodosFinalizado(){
        List<Pedido> pedidosRecebidos = pedidoRepositoryPort.buscaPedidosPorStatus(StatusPedido.FINALIZADO);
        return pedidosRecebidos.stream().map(Pedido::toPedidoDTO).collect(Collectors.toList());
    }
    //-------------------------------------------------------------------------//
    //                       MANIPULAÇÃO DE STATUS
    //-------------------------------------------------------------------------//
    @Override
    public PedidoDTO receber(UUID codigo) throws Exception {
        Pedido pedido = this.buscar(codigo);
        if(pedido.getStatus().equals(StatusPedido.INICIADO)){
            pedido.atualizarStatus(StatusPedido.RECEBIDO);
            // TODO - CHAMADA PARA PAGAMENTO
        }else{
            throw new InvalidStatusException("Status inválido!");
        }
        return pedidoRepositoryPort.salvar(pedido).toPedidoDTO();
    }
    @Override
    public PedidoDTO aprovar(UUID codigo) throws Exception {
        Pedido pedido = this.buscar(codigo);
        if(pedido.getStatus().equals(StatusPedido.RECEBIDO)){
            pedido.atualizarStatus(StatusPedido.EM_PREPARACAO);
            // TODO - CHAMADA PARA COMANDA
        }else{
            throw new InvalidStatusException("Status inválido!");
        }
        return pedidoRepositoryPort.salvar(pedido).toPedidoDTO();
    }
    @Override
    public PedidoDTO prontificar(UUID codigo) throws Exception {
        Pedido pedido = this.buscar(codigo);
        if(pedido.getStatus().equals(StatusPedido.EM_PREPARACAO)){
            pedido.atualizarStatus(StatusPedido.PRONTO);
        }else{
            throw new InvalidStatusException("Status inválido!");
        }
        return pedidoRepositoryPort.salvar(pedido).toPedidoDTO();
    }
    @Override
    public PedidoDTO finalizar(UUID codigo) throws Exception {
        Pedido pedido = this.buscar(codigo);
        if(pedido.getStatus().equals(StatusPedido.PRONTO)){
            pedido.atualizarStatus(StatusPedido.FINALIZADO);
        }else{
            throw new InvalidStatusException("Status inválido!");
        }
        return pedidoRepositoryPort.salvar(pedido).toPedidoDTO();
    }
    //-------------------------------------------------------------------------//
    //                          MÉTODOS AUXILIARES
    //-------------------------------------------------------------------------//
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
    @Override
    public Integer calcularTempoTotalPreparo(UUID codigo) {
        PedidoDTO pedido = this.buscaPedido(codigo);
        Integer retorno = 0;

        //Foreach para percorrer os itens e calcular o valor total
        List<ItemPedido> listaItens = pedido.getItens();
        for (ItemPedido i : listaItens) {
            retorno += i.getTempoPreparoMin() * i.getQuantidade();
        }
        return retorno;
    }
    private ItemPedido getItemPedidoByProduto(UUID codigoProduto, List<ItemPedido> itemPedidos){
        return itemPedidos.stream()
                .filter(itemPedido -> itemPedido.getCodigo().getProdutoCodigo().equals(codigoProduto))
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca pedido pelo código e retorna um objeto de Pedido
     * Utilizar pora tratamentos internos somente para facilitar a busca minimizando a necessidade
     * de alterar o tipo de objeto
     * @param codigo
     * @return
     */
    private Pedido buscar(UUID codigo) {
        Optional<Pedido> optionalPedido = pedidoRepositoryPort.buscaPedido(codigo);
        optionalPedido.orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado!"));
        return optionalPedido.get();
    }
    @Override
    public List<ItemPedidoDTO> listarItens(UUID codigo) {
        return this.buscaPedido(codigo).getItens().stream().map(ItemPedido::toItemPedidoDTO).collect(Collectors.toList());
    }
}
