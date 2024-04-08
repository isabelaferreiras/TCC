package com.isabela.TCC.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Empresa empresa;
    private String decricao;
    private String cargo;
    private Situacao situacao = Situacao.NAO_ATIVO;
    @ManyToMany
    private Set<Profissional> profissionais;

    public Vaga() {
    }

    public Vaga(String titulo, Empresa empresa, String decricao, String cargo, Situacao situacao) {
        this.titulo = titulo;
        this.empresa = empresa;
        this.decricao = decricao;
        this.cargo = cargo;
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return id.equals(vaga.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
