package com.isabela.TCC.domain;

import com.isabela.TCC.domain.Empresa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Empresa empresa;
    private String decricao;
    private String cargo;
    private Situacao situacao = Situacao.NAO_ATIVO;

    public Vaga() {
    }

    public Vaga(String titulo, Empresa empresa, String decricao, String cargo, Situacao situacao) {
        this.titulo = titulo;
        this.empresa = empresa;
        this.decricao = decricao;
        this.cargo = cargo;
        this.situacao = situacao;
    }
}
