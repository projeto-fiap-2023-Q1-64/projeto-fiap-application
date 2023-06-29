package br.fiap.projeto.contexto.produto.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepositoryPort;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepositoryPort;
import br.fiap.projeto.contexto.produto.infrastructure.entity.ProdutoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class PostgresProdutoRepository implements ProdutoRepositoryPort {
public class PostgresProdutoRepository implements ProdutoRepositoryPort {

    private final SpringProdutoRepository springProdutoRepository;

    @Autowired
    public PostgresProdutoRepository(SpringProdutoRepository springProdutoRepository) {
    public PostgresProdutoRepository(SpringProdutoRepository springProdutoRepository) {
        this.springProdutoRepository = springProdutoRepository;
    }

    @Override
    public List<Produto> buscaTodos() {
        List<ProdutoEntity> resultados = springProdutoRepository.findAll();
        return resultados.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Produto buscaProduto(String codigo) {
        Optional<ProdutoEntity> produtoEntity = springProdutoRepository.findByCodigo(codigo);
        produtoEntity.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));
        return produtoEntity.get().toProduto();
    }

    @Override
    public List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        List<ProdutoEntity> resultados = springProdutoRepository.findByCategoria(categoria);
        return resultados.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    public List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        List<ProdutoEntity> resultados = springProdutoRepository.findByCategoria(categoria);
        return resultados.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public List<String> buscaCategoriasDeProdutos() {
        return Arrays.stream(CategoriaProduto.values()).map(c -> c.name()).collect(Collectors.toList());
        return Arrays.stream(CategoriaProduto.values()).map(c -> c.name()).collect(Collectors.toList());
    }

    @Override
    public Produto criaProduto(Produto produto) {
        ProdutoEntity produtoSalvo = springProdutoRepository.save(new ProdutoEntity(produto));
        return produtoSalvo.toProduto();
    public Produto criaProduto(Produto produto) {
        ProdutoEntity produtoSalvo = springProdutoRepository.save(new ProdutoEntity(produto));
        return produtoSalvo.toProduto();
    }

    @Transactional
    @Override
    public void removeProduto(String codigo) {
        Optional<ProdutoEntity> produtoEntity = springProdutoRepository.findByCodigo(codigo);
        produtoEntity.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));
        springProdutoRepository.deleteByCodigo(codigo);
    }

    @Override
    public void atualizaProduto(String codigo, Produto produto) {
        Optional<ProdutoEntity> produtoEntity = springProdutoRepository.findByCodigo(codigo);
        produtoEntity.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));
        produtoEntity.get().atualizar(produto);
        springProdutoRepository.save(produtoEntity.get());
    }
}
