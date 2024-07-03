package com.isabela.TCC.exceptions;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class LimiteDeInscricoesAtingido extends RuntimeException{
    public LimiteDeInscricoesAtingido(String mensagem) {
        super(mensagem);
    }

}
