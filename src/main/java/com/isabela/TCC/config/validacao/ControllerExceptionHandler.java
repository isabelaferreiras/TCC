package com.isabela.TCC.config.validacao;

import com.isabela.TCC.exceptions.LimiteDeInscricoesAtingido;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validacao(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError erro = new ValidationError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setErro("Validation Exception!");
        erro.setMensagem("Dados inválidos");
        erro.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()){
            erro.adicionarErro(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> baseDeDados(EntityNotFoundException e, HttpServletRequest request){
        StandardError erro = new StandardError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setErro("Entity exception!");
        erro.setMensagem(e.getMessage());
        erro.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(LimiteDeInscricoesAtingido.class)
    public ResponseEntity<String> handleLimiteDeInscricoesAtingidoException(LimiteDeInscricoesAtingido ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
