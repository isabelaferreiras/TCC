package com.isabela.TCC.domain.vaga.model;

import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.enums.Situacao;
import com.isabela.TCC.domain.empresa.model.Empresa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    private Empresa empresa;
    private String decricao;
    private String cargo;
    private Situacao situacao = Situacao.NAO_ATIVO;
    @ManyToMany(mappedBy = "vagas")
    private Set<Profissional> profissionais;


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
