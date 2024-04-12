package com.isabela.TCC.utils;

import com.isabela.TCC.enums.Nivel;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Idioma {
    private String idioma;
    private Nivel nivelLeitura;
    private Nivel nivelConvesacao;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idioma idioma1 = (Idioma) o;
        return idioma.equals(idioma1.idioma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idioma);
    }
}
