package br.fiap.projeto.contexto.produto.application.rest;

import br.fiap.projeto.contexto.produto.application.rest.request.ProdutoDTORequest;
import br.fiap.projeto.contexto.produto.application.rest.response.ProdutoDTOResponse;
import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoServicePort;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoServicePort produtoServicePort;

    @Autowired
    public ProdutoController(ProdutoServicePort produtoServicePort) {
        this.produtoServicePort = produtoServicePort;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProdutoDTOResponse>> getProdutos() {
        List<Produto> lista = this.produtoServicePort.buscaTodos();
        List<ProdutoDTOResponse> listaDTO = lista.stream().map(ProdutoDTOResponse::newInstanseByProduto).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }

    @SneakyThrows
    @GetMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<ProdutoDTOResponse> getProduto(@PathVariable("codigo") String codigo) {
        Produto produto = this.produtoServicePort.buscaProduto(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoDTOResponse.newInstanseByProduto(produto));
    }

    @GetMapping("/por-categoria")
    @ResponseBody
    public ResponseEntity<List<ProdutoDTOResponse>> getProdutosPorCategoria(@PathParam("categoria") CategoriaProduto categoria) {
        List<Produto> lista = this.produtoServicePort.buscaProdutosPorCategoria(categoria);
        List<ProdutoDTOResponse> listaDTO = lista.stream().map(ProdutoDTOResponse::newInstanseByProduto).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/categorias")
    @ResponseBody
    public ResponseEntity<List<String>> getCategoriasDeProdutos() {
        List<String> lista = this.produtoServicePort.getCategoriasDeProdutos();
        return ResponseEntity.ok(lista);
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<ProdutoDTOResponse> criaProduto(@RequestBody ProdutoDTORequest produtoDTORequest) {
        Produto produtoCriado = this.produtoServicePort.criaProduto(produtoDTORequest.toProduto());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoDTOResponse.newInstanseByProduto(produtoCriado));
    }

    @SneakyThrows
    @Transactional
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removeProduto(@PathVariable("codigo") String codigo) {
        this.produtoServicePort.removeProduto(codigo);
        return ResponseEntity.noContent().build();
    }

    @SneakyThrows
    @PutMapping("/{codigo}")
    public ResponseEntity<Void> atualizaProduto(@PathVariable String codigo, @RequestBody ProdutoDTORequest produtoDTO) {
        this.produtoServicePort.atualizaProduto(codigo, produtoDTO.toProduto());
        return ResponseEntity.ok().build();
    }
}
