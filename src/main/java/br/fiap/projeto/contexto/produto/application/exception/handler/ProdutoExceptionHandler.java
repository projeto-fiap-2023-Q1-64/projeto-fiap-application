package br.fiap.projeto.contexto.produto.application.exception.handler;

import br.fiap.projeto.contexto.produto.application.exception.ProdutoResponseException;
import br.fiap.projeto.contexto.produto.application.rest.ProdutoController;
import br.fiap.projeto.contexto.produto.domain.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.produto.domain.exception.ProdutoNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ProdutoController.class)
public class ProdutoExceptionHandler {
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ProdutoResponseException> handleEntityNotFoundException(ProdutoNaoEncontradoException e) {
        ProdutoResponseException response = new ProdutoResponseException(e.getCode(), e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EntradaInvalidaException.class)
    public ResponseEntity<ProdutoResponseException> handleEntradaInvalidaException(EntradaInvalidaException e) {
        ProdutoResponseException response = new ProdutoResponseException(e.getCode(), e.getMessage(), e.getCause());
        return ResponseEntity.unprocessableEntity().body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProdutoResponseException> handleDataIntegrityViolationException(Exception e) {
        ProdutoResponseException response = new ProdutoResponseException(3002, e.getMessage(), e.getCause());
        return ResponseEntity.unprocessableEntity().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProdutoResponseException> handleException(Exception e) {
        e.printStackTrace();
        ProdutoResponseException response = new ProdutoResponseException(3000, e.getMessage(), e.getCause());
        return ResponseEntity.internalServerError().body(response);
    }
}
