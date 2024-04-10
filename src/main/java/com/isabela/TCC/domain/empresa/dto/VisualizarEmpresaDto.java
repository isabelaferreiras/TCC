package com.isabela.TCC.domain.empresa.dto;

import com.isabela.TCC.domain.Endereco;
import com.isabela.TCC.domain.empresa.model.Empresa;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualizarEmpresaDto {
    private String email;
    private String nomeEmpresa;
    private String descricao;
    private String ramo;
    private Endereco endereco;

    public static VisualizarEmpresaDto copiarDaEntidadeProDto (Empresa entidade){
        VisualizarEmpresaDto dto = new VisualizarEmpresaDto();
        dto.nomeEmpresa = entidade.getNomeEmpresa();
        dto.email = entidade.getEmail();
        dto.descricao = entidade.getDescricao();
        dto.ramo = entidade.getRamo();
        dto.endereco = entidade.getEndereco();

        return dto;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualizarEmpresaDto that = (VisualizarEmpresaDto) o;
        return nomeEmpresa.equals(that.nomeEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeEmpresa);
    }
}
