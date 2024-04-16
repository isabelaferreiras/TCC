package com.isabela.TCC.domain.curriculo.model;

import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.enums.Escolaridade;
import com.isabela.TCC.utils.ExperienciaProfissional;
import com.isabela.TCC.utils.HabilidadePessoal;
import com.isabela.TCC.utils.HabilidadeTecnica;
import com.isabela.TCC.utils.Idioma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_curriculo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;
    private Escolaridade escolaridade;
    @ElementCollection
    private Set<ExperienciaProfissional> experienciasProfissionais = new HashSet<>();
    @ElementCollection
    private Set<HabilidadePessoal> habilidadesPessoais = new HashSet<>();
    @ElementCollection
    private Set<HabilidadeTecnica> habilidadesTecnicas = new HashSet<>();
    @ElementCollection
    private Set<Idioma> idiomas = new HashSet<>();
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


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
