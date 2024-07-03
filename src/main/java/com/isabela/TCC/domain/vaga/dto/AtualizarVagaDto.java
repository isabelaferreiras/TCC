package com.isabela.TCC.domain.vaga.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(min = 10, max = 400)
    private String descricao;
    @NotBlank
    @Size(min = 4, max = 20)
    private String cargo;
    private Integer limiteProfissionais;
}
