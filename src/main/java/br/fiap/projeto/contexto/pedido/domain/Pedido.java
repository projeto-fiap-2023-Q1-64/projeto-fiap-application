package br.fiap.projeto.contexto.pedido.domain;

import java.util.List;
import java.util.UUID;

import br.fiap.projeto.contexto.pedido.domain.dto.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;

public class Pedido {
 
	private UUID codigo;
	private List<ItemPedido> itens;
	private UUID cliente;
	private StatusPedido status;
	private Double valorTotal;
	public Pedido (	UUID codigo
				, List<ItemPedido> itens
				, UUID cliente
				, StatusPedido status
				, Double valorTotal ){
		this.codigo = codigo;
		this.itens = itens;
		this.cliente = cliente;
		this.status = status;
		this.valorTotal = valorTotal;
	}
    public Pedido(PedidoDTO pedidoDTO) {
		this.codigo = pedidoDTO.getCodigo();
		this.itens = pedidoDTO.getItens();
		this.cliente = pedidoDTO.getCliente();
		this.status = pedidoDTO.getStatus();
		this.valorTotal = pedidoDTO.getValorTotal();
    }
	public UUID getCodigo() {return codigo;}
	public List<ItemPedido> getItens() {return itens;}
	public UUID getCliente() {return cliente;}
	public StatusPedido getStatus() {return status;}
	public Double getValorTotal() {return valorTotal;}
	public Double calcularValorTotal() {
		return null;
	}
	public void aumentarQuantidade(ProdutoPedido produto) {}
	public void reduzirQuantidade(ProdutoPedido produto) {}
	public Integer calcularTempoTotalPreparo() {return null;}
	public void adicionarProduto(ProdutoPedido produto) {}
	public void removerProduto(ProdutoPedido produto) {}
	public List<ItemPedido> listarItens() {
		return null;
	}
	public PedidoDTO toPedidoDTO() {
		return new PedidoDTO( codigo, itens, cliente, status, valorTotal);
	}
}
 
