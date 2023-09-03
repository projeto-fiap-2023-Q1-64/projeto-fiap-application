package br.fiap.projeto.contexto.pedido.external.api.exception.handler;

import br.fiap.projeto.contexto.pedido.external.api.PedidoManagementApiController;
import br.fiap.projeto.contexto.pedido.external.api.exception.PedidoResponseException;
import br.fiap.projeto.contexto.pedido.usecase.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = PedidoManagementApiController.class)
public class PedidoControllerExceptionHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<PedidoResponseException> handleItemNaoEncontrado(Exception e) {
        PedidoResponseException response = new PedidoResponseException(1001, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<PedidoResponseException> handleEntradaInvalidaException(Exception e) {
        PedidoResponseException response = new PedidoResponseException(1003, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<PedidoResponseException> handleDataIntegrityViolationException(Exception e) {
        PedidoResponseException response = new PedidoResponseException(1002, e.getMessage(), e.getCause());
        return ResponseEntity.unprocessableEntity().body(response);
    }

    @ExceptionHandler(NoItensException.class)
    public ResponseEntity<PedidoResponseException> handleNoItensException(Exception e) {
        PedidoResponseException response = new PedidoResponseException(1004, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(response);
    }

    @ExceptionHandler(InvalidOperacaoProdutoException.class)
    public ResponseEntity<PedidoResponseException> handleInvalidOperationException(Exception e) {
        PedidoResponseException response = new PedidoResponseException(1004, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(response);
    }

    @ExceptionHandler(IntegrationProdutoException.class)
    public ResponseEntity<PedidoResponseException> handleIntegrationProdutoException(Exception e) {
        PedidoResponseException response = new PedidoResponseException(1005, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(response);
    }

    @ExceptionHandler(IntegrationPagamentoException.class)
    public ResponseEntity<PedidoResponseException> handleIntegrationPagamentoException(Exception e) {
        PedidoResponseException response = new PedidoResponseException(1005, e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PedidoResponseException> handleException(Exception e) {
        e.printStackTrace();
        PedidoResponseException response = new PedidoResponseException(1000, e.getMessage(), e.getCause());
        return ResponseEntity.internalServerError().body(response);
    }
}
