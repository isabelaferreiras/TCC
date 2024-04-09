package com.isabela.TCC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciaProfissional {
    private String cargo;
    private String empresa;
    private Endereco endereco;
    private String descricao;
    private LocalDate comeco;
    private LocalDate fim;
    private BigDecimal salario;

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
