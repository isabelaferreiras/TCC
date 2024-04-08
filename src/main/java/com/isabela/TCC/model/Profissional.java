package com.isabela.TCC.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String senha;
    private Endereco endereco;
    private String imagem;
    private Curriculo curriculo;
    @ManyToMany
    private Set<Vaga> vagas;

    public Profissional() {
    }

    public Profissional(String nome, LocalDate dataNascimento, String email, String senha, Endereco endereco, String imagem, Curriculo curriculo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.imagem = imagem;
        this.curriculo = curriculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissional that = (Profissional) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
