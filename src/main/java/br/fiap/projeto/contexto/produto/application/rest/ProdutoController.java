package br.fiap.projeto.contexto.produto.application.rest;

import br.fiap.projeto.contexto.produto.application.rest.dto.ProdutoDTO;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<List<ProdutoDTO>> getProdutos() {
        List<ProdutoDTO> lista = this.produtoServicePort.buscaTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<ProdutoDTO> getProduto(@PathVariable("codigo") String codigo) {
        ProdutoDTO produtoDTO = this.produtoServicePort.buscaProduto(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTO);
    }

    @GetMapping("/por-categoria")
    @ResponseBody
    public ResponseEntity<List<ProdutoDTO>> getProdutosPorCategoria(@PathParam("categoria") CategoriaProduto categoria) {
        List<ProdutoDTO> lista = this.produtoServicePort.buscaProdutosPorCategoria(categoria);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/categorias")
    @ResponseBody
    public ResponseEntity<List<String>> getCategoriasDeProdutos() {
        List<String> lista = this.produtoServicePort.getCategoriasDeProdutos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criaProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoCriado = this.produtoServicePort.criaProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    @Transactional
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removeProduto(@PathVariable("codigo") String codigo) {
        this.produtoServicePort.removeProduto(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Void> atualizaProduto(@PathVariable String codigo, @RequestBody ProdutoDTO produtoDTO) {
        this.produtoServicePort.atualizaProduto(codigo, produtoDTO);
        return ResponseEntity.ok().build();
    }
}
