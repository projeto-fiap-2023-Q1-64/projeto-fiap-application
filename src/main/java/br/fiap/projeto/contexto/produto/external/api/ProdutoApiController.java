package br.fiap.projeto.contexto.produto.external.api;

import br.fiap.projeto.contexto.produto.adapter.controller.port.IProdutoRestAdapterController;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.request.ProdutoDTORequest;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.response.ProdutoDTOResponse;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"Produtos"}, description = "Endpoints do domínio de Produtos")
public class ProdutoApiController {

    private final IProdutoRestAdapterController produtoAdapterController;

    @Autowired
    public ProdutoApiController(IProdutoRestAdapterController produtoAdapterController) {
        this.produtoAdapterController = produtoAdapterController;
    }

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Busca todos os produtos", notes = "Este endpoint retorna todos os produtos cadastrados")
    public ResponseEntity<List<ProdutoDTOResponse>> getProdutos() {
        return ResponseEntity.ok().body(this.produtoAdapterController.buscaTodos());
    }

    @SneakyThrows
    @GetMapping("/{codigo}")
    @ResponseBody
    @ApiOperation(value = "Busca por produto", notes = "Este endpoint busca um produto pelo seu código")
    public ResponseEntity<ProdutoDTOResponse> getProduto(@PathVariable("codigo") String codigo) {
        return ResponseEntity.status(HttpStatus.OK).body(this.produtoAdapterController.buscaProduto(codigo));
    }

    @GetMapping("/por-categoria")
    @ResponseBody
    @ApiOperation(value = "Busca produtos por categoria", notes = "Este endpoint busca produtos filtrando por categoria")
    public ResponseEntity<List<ProdutoDTOResponse>> getProdutosPorCategoria(@PathParam("categoria") CategoriaProduto categoria) {
        return ResponseEntity.ok(this.produtoAdapterController.buscaProdutosPorCategoria(categoria));
    }

    @GetMapping("/categorias")
    @ResponseBody
    @ApiOperation(value = "Busca por categorias de produtos", notes = "Este endpoint retorna uma lista de categorias existentes de produto")
    public ResponseEntity<List<String>> getCategoriasDeProdutos() {
        List<String> lista = this.produtoAdapterController.getCategoriasDeProdutos();
        return ResponseEntity.ok(lista);
    }

    @SneakyThrows
    @PostMapping
    @ApiOperation(value = "Cria produto", notes = "Este endpoint permite a criação de novos produtos")
    public ResponseEntity<ProdutoDTOResponse> criaProduto(@RequestBody ProdutoDTORequest produtoDTORequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.produtoAdapterController.criaProduto(produtoDTORequest));
    }

    @SneakyThrows
    @Transactional
    @DeleteMapping("/{codigo}")
    @ApiOperation(value = "Remove produto", notes = "Este endpoint permite remover produtos do cadastro")
    public ResponseEntity<Void> removeProduto(@PathVariable("codigo") String codigo) {
        this.produtoAdapterController.removeProduto(codigo);
        return ResponseEntity.noContent().build();
    }

    @SneakyThrows
    @PutMapping("/{codigo}")
    @ApiOperation(value = "Atualiza produto", notes = "Este endpoint permite a atualização de produtos do cadastro")
    public ResponseEntity<Void> atualizaProduto(@PathVariable String codigo, @RequestBody ProdutoDTORequest produtoDTO) {
        this.produtoAdapterController.atualizaProduto(codigo, produtoDTO);
        return ResponseEntity.ok().build();
    }
}
