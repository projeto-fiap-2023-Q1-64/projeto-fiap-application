package br.fiap.projeto.contexto.pedido.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pedido.domain.ProdutoPedido;
import br.fiap.projeto.contexto.pedido.domain.port.repository.ProdutoPedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.infrastructure.entity.ProdutoPedidoEntity;
import br.fiap.projeto.contexto.produto.domain.Produto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class PostgresProdutoPedidoRepositoryPort implements ProdutoPedidoRepositoryPort {
    private final SpringProdutoPedidoRepository springProdutoPedidoRepository;
    public PostgresProdutoPedidoRepositoryPort(SpringProdutoPedidoRepository springProdutoPedidoRepository) {
        this.springProdutoPedidoRepository = springProdutoPedidoRepository;
    }
    @Override
    public ProdutoPedido criaProdutoPedido(ProdutoPedido produtoPedido) {
        return springProdutoPedidoRepository.save(new ProdutoPedidoEntity(produtoPedido)).toDomain();
    }
    @Override
    public ProdutoPedido buscaProdutoPedido(UUID codigo) {
        Optional<ProdutoPedidoEntity> produtoPedidoEntity = springProdutoPedidoRepository.findByCodigo(codigo);
        produtoPedidoEntity.orElseThrow(() -> new EntityNotFoundException("Produto Pedido não encontrado!"));
        return produtoPedidoEntity.get().toDomain();
    }
    @Override
    public List<ProdutoPedido> buscaTodos() {
        List<ProdutoPedidoEntity> listaProdutosPedidoEntity = springProdutoPedidoRepository.findAll();
        return listaProdutosPedidoEntity.stream().map(ProdutoPedidoEntity::toDomain).collect(Collectors.toList());
    }
    @Override
    public ProdutoPedido atualizaProdutoPedido(UUID codigo, ProdutoPedido produtoPedido) {
        // realiza a consulta do produtoPedido para verificar sua existência e recuperar o objeto para transformar em entidade
        ProdutoPedidoEntity produtoPedidoEntity = new ProdutoPedidoEntity(this.buscaProdutoPedido(codigo));
        produtoPedidoEntity.atualizar(produtoPedido);
        return springProdutoPedidoRepository.save(produtoPedidoEntity).toDomain();
    }
    @Override
    public void removeProdutoPedido(UUID codigo) {
        // realiza a consulta do produtoPedido para verificar sua existência
        this.buscaProdutoPedido(codigo);
        springProdutoPedidoRepository.deleteByCodigo(codigo);
    }
}