package com.isabela.TCC.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Profissional profissional;
    private Escolaridade escolaridade;
    @OneToMany
    private Set<ExperienciaProfissional> experienciasProfissipnais;
    @OneToMany
    private Set<HabilidadePessoal> habilidadesPessoais;
    @OneToMany
    private Set<HabilidadeTecnica> habilidadesTecnicas;
    @OneToMany
    private Set<Idioma> idiomas;


    public Curriculo() {
    }

    public Curriculo(Profissional profissional, Escolaridade escolaridade, Set<ExperienciaProfissional> experienciasProfissipnais, Set<HabilidadePessoal> habilidadesPessoais, Set<HabilidadeTecnica> habilidadesTecnicas, Set<Idioma> idiomas) {
        this.profissional = profissional;
        this.escolaridade = escolaridade;
        this.experienciasProfissipnais = experienciasProfissipnais;
        this.habilidadesPessoais = habilidadesPessoais;
        this.habilidadesTecnicas = habilidadesTecnicas;
        this.idiomas = idiomas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curriculo curriculo = (Curriculo) o;
        return id.equals(curriculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
