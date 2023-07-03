package br.fiap.projeto.contexto.pedido.domain.dto;

import br.fiap.projeto.contexto.pedido.domain.enums.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProdutoPedidoDTO {
    private UUID codigo;
    private String nome;
    private String descricao;
    private Double preco;
    private CategoriaProduto categoria;
    private String imagem;
    private Integer tempoPreparoMin;
}