package br.fiap.projeto.contexto.comanda.infrastructure.repository.postgres;

import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.fiap.projeto.contexto.comanda.domain.ProdutoComanda;
import br.fiap.projeto.contexto.comanda.domain.port.repository.ProdutoComandaRepositoryPort;
import br.fiap.projeto.contexto.comanda.infrastructure.entity.ProdutoComandaEntity;

@Component
@Primary
public class PostgresProdutoComandaRepository implements ProdutoComandaRepositoryPort {

    private final SpringProdutoComandaRepository springProdutoComandaRepository;

    public PostgresProdutoComandaRepository(SpringProdutoComandaRepository springProdutoComandaRepository) {
        this.springProdutoComandaRepository = springProdutoComandaRepository;
    }

    @Override
    public ProdutoComanda criaProdutoComanda(ProdutoComanda produto) {
        ProdutoComandaEntity produtoComanda = springProdutoComandaRepository.save(new ProdutoComandaEntity(produto));
        return produtoComanda.toProdutoComanda();
    }

    @Override
    public ProdutoComanda buscaProdutoComanda(UUID codigo) {
        ProdutoComandaEntity resultados = springProdutoComandaRepository.findByStatus(codigo);
        return resultados.toProdutoComanda();
    }
}