package com.isabela.TCC.domain.curriculo.model;

import com.isabela.TCC.domain.*;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.enums.Escolaridade;
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
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Profissional profissional;
    private Escolaridade escolaridade;
    @ElementCollection
    private Set<ExperienciaProfissional> experienciasProfissionais;
    @ElementCollection
    private Set<HabilidadePessoal> habilidadesPessoais;
    @ElementCollection
    private Set<HabilidadeTecnica> habilidadesTecnicas;
    @ElementCollection
    private Set<Idioma> idiomas;


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
