package com.isabela.TCC.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    private String nomeEmpresa;

    private Endereco endereco;
    private String descricao;
    private String ramo;
    private Situacao situacao = Situacao.NAO_ATIVO;
    @OneToMany
    private Set<Vaga> vagas;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    public Empresa() {
    }

    public Empresa(String email, String senha, String nomeEmpresa, Endereco endereco, String descricao, String ramo, Situacao situacao, Set<Vaga> vagas, LocalDateTime createAt, LocalDateTime updateAt) {
        this.email = email;
        this.senha = senha;
        this.nomeEmpresa = nomeEmpresa;
        this.endereco = endereco;
        this.descricao = descricao;
        this.ramo = ramo;
        this.situacao = situacao;
        this.vagas = vagas;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
