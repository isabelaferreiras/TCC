package com.isabela.TCC.config.validacao;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidacaoDoErro> validacao(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidacaoDoErro erro = new ValidacaoDoErro();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setErro("Exceção de validação");
        erro.setMensagem("Dados inválidos");
        erro.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()){
            erro.adicionarErro(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<FormatacaoErroPadrao>


}
