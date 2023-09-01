package br.fiap.projeto.contexto.produto.external.api.exception.handler;

import br.fiap.projeto.contexto.produto.external.api.ProdutoApiController;
import br.fiap.projeto.contexto.produto.external.api.exception.ProdutoResponseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice(basePackageClasses = ProdutoApiController.class)
public class ProdutoControllerExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProdutoResponseException> handleEntityNotFoundException(Exception e) {
        ProdutoResponseException response = new ProdutoResponseException(3001, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
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
