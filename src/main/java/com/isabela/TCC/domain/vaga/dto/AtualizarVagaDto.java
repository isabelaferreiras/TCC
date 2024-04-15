package com.isabela.TCC.domain.vaga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AtualizarVagaDto {
    private String titulo;
    private String descricao;
    private String cargo;
    private LocalDateTime updateAt;
}
