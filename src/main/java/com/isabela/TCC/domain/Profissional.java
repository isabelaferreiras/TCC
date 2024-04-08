package com.isabela.TCC.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.DataTruncation;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
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
}
