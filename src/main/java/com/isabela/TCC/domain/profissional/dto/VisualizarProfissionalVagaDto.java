package com.isabela.TCC.domain.profissional.dto;

import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.utils.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualizarProfissionalVagaDto {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private Endereco endereco;

    public static VisualizarProfissionalVagaDto copiarDaEntidadeProDto (Profissional entidade){
        VisualizarProfissionalVagaDto dto = new VisualizarProfissionalVagaDto();
        dto.id = entidade.getId();
        dto.nome = entidade.getNome();
        dto.dataNascimento = entidade.getDataNascimento();
        dto.email = entidade.getEmail();
        dto.endereco = entidade.getEndereco();

        return dto;
    }
}
