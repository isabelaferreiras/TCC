package com.isabela.TCC.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExperienciaProfissional {
    private String cargo;
    private String empresa;
    private Endereco endereco;
    private String descricao;
    private LocalDate comeco;
    private LocalDate fim;
    private BigDecimal salario;

    public ExperienciaProfissional() {
    }

    public ExperienciaProfissional(String cargo, String empresa, Endereco endereco, String descricao, LocalDate comeco, LocalDate fim, BigDecimal salario) {
        this.cargo = cargo;
        this.empresa = empresa;
        this.endereco = endereco;
        this.descricao = descricao;
        this.comeco = comeco;
        this.fim = fim;
        this.salario = salario;
    }
}
