package com.isabela.TCC.utils;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class HabilidadeTecnica {
    private String habilidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HabilidadeTecnica that = (HabilidadeTecnica) o;
        return habilidade.equals(that.habilidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habilidade);
    }
}
