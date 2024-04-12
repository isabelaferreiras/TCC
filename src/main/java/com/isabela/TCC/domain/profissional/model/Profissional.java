package com.isabela.TCC.domain.profissional.model;

import com.isabela.TCC.utils.Endereco;
import com.isabela.TCC.domain.curriculo.model.Curriculo;
import com.isabela.TCC.domain.vaga.model.Vaga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "tb_profissional")
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
    @Embedded
    private Endereco endereco;
    private String imagem;
    @OneToOne(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    private Curriculo curriculo;
    @ManyToMany(mappedBy = "profissionais", cascade = CascadeType.ALL)
    private Set<Vaga> vagas = new HashSet<>();
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


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
