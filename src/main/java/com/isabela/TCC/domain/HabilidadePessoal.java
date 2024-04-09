package com.isabela.TCC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabilidadePessoal {

    private String habilidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HabilidadePessoal that = (HabilidadePessoal) o;
        return habilidade.equals(that.habilidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habilidade);
    }
}
