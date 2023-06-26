package br.fiap.projeto.contexto.pedido.domain;

import br.fiap.projeto.contexto.pedido.domain.dto.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.enums.CategoriaProduto;

import java.util.UUID;

public class ProdutoPedido {
 
	private UUID codigo;
	private String nome;
	private String descricao;
	private Double preco;
	private CategoriaProduto categoria;
	private String imagem;
	private Integer tempoPreparoMin;
	public ProdutoPedido( UUID codigo
						, String nome
						, String descricao
						, Double preco
						, CategoriaProduto categoria
						, String imagem
						, Integer tempoPreparoMin){
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
		this.imagem = imagem;
		this.tempoPreparoMin = tempoPreparoMin;
	}

	public ProdutoPedido(ProdutoPedidoDTO produtoPedidoDTO) {
		this.codigo = produtoPedidoDTO.getCodigo();
		this.nome = produtoPedidoDTO.getNome();
		this.descricao = produtoPedidoDTO.getDescricao();
		this.preco = produtoPedidoDTO.getPreco();
		this.categoria = produtoPedidoDTO.getCategoria();
		this.imagem = produtoPedidoDTO.getImagem();
		this.tempoPreparoMin = produtoPedidoDTO.getTempoPreparoMin();
	}
	public UUID getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public CategoriaProduto getCategoria() {
		return categoria;
	}
	public String getImagem() {
		return imagem;
	}
	public Integer getTempoPreparoMin() {
		return tempoPreparoMin;
	}
	public ProdutoPedidoDTO toProdutoPedidoDTO() {
		return new ProdutoPedidoDTO( codigo, nome, descricao, preco, categoria, imagem, tempoPreparoMin );
	}
}
 
