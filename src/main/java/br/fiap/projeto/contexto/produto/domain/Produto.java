package br.fiap.projeto.contexto.produto.domain;

import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;

public class Produto {
 
	private Long codigo;
	 
	private String nome;
	 
	private String descricao;
	 
	private Double preco;
	 
	private CategoriaProduto categoria;
	 
	private String imagem;
	 
	private Integer tempoPreparoMin;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public CategoriaProduto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Integer getTempoPreparoMin() {
		return tempoPreparoMin;
	}

	public void setTempoPreparoMin(Integer tempoPreparoMin) {
		this.tempoPreparoMin = tempoPreparoMin;
	}
	 
}
 
