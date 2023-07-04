package br.fiap.projeto.contexto.produto.infrastructure.configuration;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.infrastructure.repository.postgres.PostgresProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
public class PostgresProdutoDataLoader {

    @Autowired
    private PostgresProdutoRepository produtoRepository;

    @PostConstruct
    public void init() {
        List<Produto> list = Arrays.asList(new Produto("28894d3e-5f18-40da-93c7-49440b91f36b", "Podrão", "Tudo que tem direito", 1.0, CategoriaProduto.LANCHE, "https://via.placeholder.com/200/200", 12));
        list.stream().forEach(p -> produtoRepository.criaProduto(p));
    }
}
