package com.isabela.TCC.domain.empresa.dto;

import com.isabela.TCC.domain.vaga.dto.VisualizarVagasDaEmpresaDto;
import com.isabela.TCC.utils.Endereco;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.enums.Situacao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualizarEmpresaDto {
    private Long id;
    private String email;
    private String nomeEmpresa;
    private String descricao;
    private String ramo;
    private Endereco endereco;
    private String cnpj;
    private Situacao situacao = Situacao.NAO_ATIVO;
    private Set<VisualizarVagasDaEmpresaDto> vagas;

    public static VisualizarEmpresaDto copiarDaEntidadeProDto (Empresa entidade){
        VisualizarEmpresaDto dto = new VisualizarEmpresaDto();
        dto.id = entidade.getId();
        dto.nomeEmpresa = entidade.getNomeEmpresa();
        dto.email = entidade.getEmail();
        dto.descricao = entidade.getDescricao();
        dto.ramo = entidade.getRamo();
        dto.endereco = entidade.getEndereco();
        dto.cnpj = entidade.getCnpj();
        dto.situacao = entidade.getSituacao();
        dto.vagas = entidade.getVagas().stream().map(VisualizarVagasDaEmpresaDto::copiarDaEntidadeProDto).collect(Collectors.toSet());

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
