package com.isabela.TCC.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienciaProfissional that = (ExperienciaProfissional) o;
        return cargo.equals(that.cargo) && empresa.equals(that.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargo, empresa);
    }
}
