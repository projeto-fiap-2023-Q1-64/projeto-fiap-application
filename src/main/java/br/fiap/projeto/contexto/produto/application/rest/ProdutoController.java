package br.fiap.projeto.contexto.produto.application.rest;

import br.fiap.projeto.contexto.produto.domain.dto.ProdutoDTO;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

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
    ResponseEntity<List<ProdutoDTO>> getProdutos() {
        List<ProdutoDTO> lista = this.produtoServicePort.buscaTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{codigo}")
    @ResponseBody
    ResponseEntity<ProdutoDTO> getProduto(@PathVariable("codigo") UUID codigo) {
        ProdutoDTO produtoDTO = this.produtoServicePort.buscaProduto(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTO);
    }

    @GetMapping("/por-categoria")
    @ResponseBody
    ResponseEntity<List<ProdutoDTO>> getProdutosPorCategoria(@PathParam("categoria") CategoriaProduto categoria) {
        List<ProdutoDTO> lista = this.produtoServicePort.buscaProdutosPorCategoria(categoria);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/categorias")
    @ResponseBody
    ResponseEntity<List<String>> getCategoriasDeProdutos() {
        List<String> lista = this.produtoServicePort.getCategoriasDeProdutos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    ResponseEntity<ProdutoDTO> criaProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoCriado = this.produtoServicePort.criaProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    @DeleteMapping("/{codigo}")
    ResponseEntity<Void> removeProduto(@PathVariable("codigo") UUID codigo) {
        this.produtoServicePort.removeProduto(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    ResponseEntity<Void> atualizaProduto(@PathVariable UUID codigo, @RequestBody ProdutoDTO produtoDTO) {
        this.produtoServicePort.atualizaProduto(codigo, produtoDTO);
        return ResponseEntity.ok().build();
    }
}
