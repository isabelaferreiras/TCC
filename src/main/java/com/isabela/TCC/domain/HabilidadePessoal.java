package com.isabela.TCC.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class HabilidadePessoal {

    private String habilidade;

    public HabilidadePessoal() {
    }

    public HabilidadePessoal(String habilidade) {
        this.habilidade = habilidade;
    }

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
