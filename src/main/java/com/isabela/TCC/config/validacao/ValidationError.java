package com.isabela.TCC.config.validacao;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError {
    private List<FieldMessage> erros = new ArrayList<>();
    public void adicionarErro(String campo, String erro){
        erros.add(new FieldMessage(campo, erro));
    }
}
