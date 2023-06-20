package br.fiap.projeto.contexto.produto.application.rest;

import br.fiap.projeto.contexto.produto.domain.dto.ProdutoDTO;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    @ResponseBody
    List<ProdutoDTO> getProdutos() {
        return this.produtoService.buscaTodos();
    }

    @GetMapping("/{codigo}")
    @ResponseBody
    ProdutoDTO getProduto(@PathVariable("codigo") UUID codigo) {
        return this.produtoService.buscaProduto(codigo);
    }

    @GetMapping("/por-categoria")
    @ResponseBody
    List<ProdutoDTO> getProdutosPorCategoria(@PathParam("categoria") CategoriaProduto categoria) {
        return this.produtoService.buscaProdutoPorCategoria(categoria);
    }

    @GetMapping("/categorias")
    @ResponseBody
    List<String> getCategoriasDeProdutos() {
        return this.produtoService.getCategoriasDeProdutos();
    }

    @PostMapping
    void criaProduto(@RequestBody ProdutoDTO produto) {
        this.produtoService.criaProduto(produto);
    }


    @DeleteMapping("/{codigo}")
    void removeProduto(@PathVariable("codigo") UUID codigo) {
        this.produtoService.removeProduto(codigo);
    }

    @PutMapping("/{codigo}")
    void atualizaProduto(@RequestBody ProdutoDTO produto) {
        this.produtoService.atualizaProduto(produto);
    }

}
