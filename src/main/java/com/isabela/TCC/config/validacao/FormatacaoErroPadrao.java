package com.isabela.TCC.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormatacaoErroPadrao {

    private Instant timestamp;
    private Integer status;
    private String erro;
    private String mensagem;
    private String path;
}
