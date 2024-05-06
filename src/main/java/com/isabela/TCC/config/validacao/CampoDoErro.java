package com.isabela.TCC.config.validacao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CampoDoErro {

    private String campo;
    private String erro;
}
