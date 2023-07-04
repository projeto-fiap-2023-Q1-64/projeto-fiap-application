package br.fiap.projeto.contexto.comanda.domain.dto;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.Comanda;
import br.fiap.projeto.contexto.comanda.domain.ItemComanda;
import br.fiap.projeto.contexto.comanda.domain.ItemComandaChave;
import br.fiap.projeto.contexto.comanda.domain.ProdutoComanda;
import br.fiap.projeto.contexto.produto.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemComandaDTO {
    private UUID codigoPedido;

    private ProdutoComandaDTO produto;

    private int qtde;

    private Comanda comanda;

    public ItemComanda toItemComanda() {
        ProdutoComanda produtoComanda = new ProdutoComanda(this.produto);
        return new ItemComanda(codigoPedido, produtoComanda, qtde, comanda);
    }

}
