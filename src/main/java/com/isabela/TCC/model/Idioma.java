package com.isabela.TCC.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Idioma {
    private String idioma;
    private Nivel nivelLeitura;
    private Nivel nivelConvesacao;

    public Idioma() {
    }

    public Idioma(String idioma, Nivel nivelLeitura, Nivel nivelConvesacao) {
        this.idioma = idioma;
        this.nivelLeitura = nivelLeitura;
        this.nivelConvesacao = nivelConvesacao;
    }

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
