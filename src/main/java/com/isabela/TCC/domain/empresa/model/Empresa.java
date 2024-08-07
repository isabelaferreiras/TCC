package com.isabela.TCC.domain.empresa.model;

import com.isabela.TCC.domain.usuario.model.User;
import com.isabela.TCC.utils.Endereco;
import com.isabela.TCC.enums.Situacao;
import com.isabela.TCC.domain.vaga.model.Vaga;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_empresa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nomeEmpresa;
    @Embedded
    private Endereco endereco;
    private String descricao;
    private String ramo;
    private Situacao situacao = Situacao.NAO_ATIVO;
    private String cnpj;
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<Vaga> vagas = new HashSet<>();
    @OneToOne(mappedBy = "empresa", cascade = CascadeType.ALL)
    private User user;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return id.equals(empresa.id) && nomeEmpresa.equals(empresa.nomeEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeEmpresa);
    }
}
