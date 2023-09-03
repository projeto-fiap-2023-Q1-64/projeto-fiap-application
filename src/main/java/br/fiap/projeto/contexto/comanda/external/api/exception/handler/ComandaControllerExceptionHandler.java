package br.fiap.projeto.contexto.comanda.external.api.exception.handler;

import br.fiap.projeto.contexto.comanda.external.api.CriarComandaApiExternal;
import br.fiap.projeto.contexto.comanda.external.api.exception.ComandaResponseException;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaDuplicadaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.IntegracaoPedidoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice(basePackageClasses = CriarComandaApiExternal.class)
public class ComandaControllerExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ComandaResponseException> handleEntityNotFoundException(Exception e) {
        ComandaResponseException response = new ComandaResponseException(2001, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EntradaInvalidaException.class)
    public ResponseEntity<ComandaResponseException> handleEntradaInvalida(Exception e) {
        ComandaResponseException response = new ComandaResponseException(2003, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(response);
    }

    @ExceptionHandler(IntegracaoPedidoException.class)
    public ResponseEntity<ComandaResponseException> handleIntegracaoPedido(Exception e) {
        ComandaResponseException response = new ComandaResponseException(2004, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(response);
    }

    @ExceptionHandler(ComandaDuplicadaException.class)
    public ResponseEntity<ComandaResponseException> handleComandaDuplicada(Exception e) {
        ComandaResponseException response = new ComandaResponseException(2005, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ComandaResponseException> handleDataIntegrityViolationException(Exception e) {
        ComandaResponseException response = new ComandaResponseException(2002, e.getMessage(), e.getCause());
        return ResponseEntity.unprocessableEntity().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ComandaResponseException> handleException(Exception e) {
        e.printStackTrace();
        ComandaResponseException response = new ComandaResponseException(2000, e.getMessage(), e.getCause());
        return ResponseEntity.internalServerError().body(response);
    }
}
