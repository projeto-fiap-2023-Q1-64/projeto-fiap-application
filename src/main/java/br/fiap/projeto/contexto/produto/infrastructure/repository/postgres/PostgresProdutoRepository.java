package br.fiap.projeto.contexto.produto.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepository;
import br.fiap.projeto.contexto.produto.infrastructure.entity.ProdutoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class PostgresProdutoRepository implements ProdutoRepository {

    private final SpringProdutoRepository springProdutoRepository;

    @Autowired
    public PostgresProdutoRepository(SpringProdutoRepository springProdutoRepository){
        this.springProdutoRepository = springProdutoRepository;
    }

    @Override
    public List<Produto> buscaTodos() {
        List<ProdutoEntity> resultados = springProdutoRepository.findAll();
        return resultados.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Produto buscaProduto(UUID codigo) {
        return null;
    }

    @Override
    public Produto buscaProdutoPorCategoria(CategoriaProduto categoria) {
        return null;
    }

    @Override
    public List<String> buscaCategoriasDeProdutos() {
        return null;
    }

    @Override
    public void criaProduto(Produto produto) {
        springProdutoRepository.save(new ProdutoEntity(produto));
    }

    @Override
    public void removeProduto(UUID codigo) {

    }

    @Override
    public void atualizaProduto(Produto produto) {

    }
}
