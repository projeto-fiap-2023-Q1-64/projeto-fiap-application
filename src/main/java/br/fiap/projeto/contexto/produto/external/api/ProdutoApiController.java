package br.fiap.projeto.contexto.produto.external.api;

import br.fiap.projeto.contexto.produto.adapter.controller.port.IProdutoRestAdapterController;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.request.ProdutoDTORequest;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.response.ProdutoDTOResponse;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoApiController {

    private final IProdutoRestAdapterController produtoAdapterController;

    @Autowired
    public ProdutoApiController(IProdutoRestAdapterController produtoAdapterController) {
        this.produtoAdapterController = produtoAdapterController;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProdutoDTOResponse>> getProdutos() {
        return ResponseEntity.ok().body(this.produtoAdapterController.buscaTodos());
    }

    @SneakyThrows
    @GetMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<ProdutoDTOResponse> getProduto(@PathVariable("codigo") String codigo) {
        return ResponseEntity.status(HttpStatus.OK).body(this.produtoAdapterController.buscaProduto(codigo));
    }

    @GetMapping("/por-categoria")
    @ResponseBody
    public ResponseEntity<List<ProdutoDTOResponse>> getProdutosPorCategoria(@PathParam("categoria") CategoriaProduto categoria) {
        return ResponseEntity.ok(this.produtoAdapterController.buscaProdutosPorCategoria(categoria));
    }

    @GetMapping("/categorias")
    @ResponseBody
    public ResponseEntity<List<String>> getCategoriasDeProdutos() {
        List<String> lista = this.produtoAdapterController.getCategoriasDeProdutos();
        return ResponseEntity.ok(lista);
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<ProdutoDTOResponse> criaProduto(@RequestBody ProdutoDTORequest produtoDTORequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.produtoAdapterController.criaProduto(produtoDTORequest));
    }

    @SneakyThrows
    @Transactional
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removeProduto(@PathVariable("codigo") String codigo) {
        this.produtoAdapterController.removeProduto(codigo);
        return ResponseEntity.noContent().build();
    }

    @SneakyThrows
    @PutMapping("/{codigo}")
    public ResponseEntity<Void> atualizaProduto(@PathVariable String codigo, @RequestBody ProdutoDTORequest produtoDTO) {
        this.produtoAdapterController.atualizaProduto(codigo, produtoDTO);
        return ResponseEntity.ok().build();
    }
}
