package com.isabela.TCC.domain.vaga.model;

import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.enums.Situacao;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.utils.Endereco;
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
@Table(name = "tb_vaga")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Embedded
    private Endereco endereco;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    private String decricao;
    private String cargo;
    private Situacao situacao = Situacao.NAO_ATIVO;
    @ManyToMany
    @JoinTable(
            name = "profissional_vaga",
            joinColumns = @JoinColumn(name = "vaga_id"),
            inverseJoinColumns = @JoinColumn(name = "profissional_id")
    )
    private Set<Profissional> profissionais = new HashSet<>();
    private Boolean limite;
    private Integer limiteProfissionais;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


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
