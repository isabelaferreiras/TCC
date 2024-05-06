package com.isabela.TCC.config.validacao;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidacaoDoErro extends FormatacaoErroPadrao{
    private List<CampoDoErro> erros = new ArrayList<>();
    public void adicionarErro(String campo, String erro){
        erros.add(new CampoDoErro(campo, erro));
    }
}
