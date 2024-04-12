package com.isabela.TCC.domain.profissional.dto;

import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.utils.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualizarProfissionalDto {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private Endereco endereco;

    public static VisualizarProfissionalDto copiarDaEntidadeProDto (Profissional entidade){
        VisualizarProfissionalDto dto = new VisualizarProfissionalDto();
        dto.id = entidade.getId();
        dto.nome = entidade.getNome();
        dto.dataNascimento = entidade.getDataNascimento();
        dto.email = entidade.getEmail();
        dto.endereco = entidade.getEndereco();

        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualizarProfissionalDto that = (VisualizarProfissionalDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
