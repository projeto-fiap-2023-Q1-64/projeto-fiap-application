package br.fiap.projeto.contexto.identificacao.application.rest.exception;

import br.fiap.projeto.contexto.identificacao.application.rest.ClienteController;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.BaseException;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.EntradaInvalidaException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ClienteController.class)
@Log4j2
public class ClienteErrorHandler {

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ResponseEntity<MensagemErroDTO> entidadeNaoEncontradaHandler(EntidadeNaoEncontradaException ex) {

        ResponseEntity<MensagemErroDTO> erro = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErroDTO(ex.getCode(), ex.getMessage()));
        return erro;
    }

    @ExceptionHandler({EntradaInvalidaException.class})
    public ResponseEntity<MensagemErroDTO> entradaInvalidaHandler(EntradaInvalidaException ex) {

        ResponseEntity<MensagemErroDTO> erro = ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new MensagemErroDTO(ex.getCode(), ex.getMessage()));
        return erro;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<MensagemErroDTO> genericHandler(EntradaInvalidaException ex) {

        ResponseEntity<MensagemErroDTO> erro = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensagemErroDTO(BaseException.DEFAULT_CODE, ex.getMessage()));

        log.error("Exceção não tratada: ", ex);
        return erro;
    }
}
