package com.isabela.TCC.domain.profissional.model;

import com.isabela.TCC.domain.Endereco;
import com.isabela.TCC.domain.curriculo.model.Curriculo;
import com.isabela.TCC.domain.vaga.model.Vaga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToOne
    private Curriculo curriculo;
    @ManyToMany
    private Set<Vaga> vagas;


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
