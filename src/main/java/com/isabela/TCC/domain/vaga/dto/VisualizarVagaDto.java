package com.isabela.TCC.domain.vaga.dto;

import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VisualizarVagaDto {

    private Long id;
    private String titulo;
    private VisualizarEmpresaDto empresa;
    private String descricao;
    private String cargo;
    private Situacao situacao = Situacao.NAO_ATIVO;
    private Set<VisualizarProfissionalDto> profissionais = new HashSet<>();

    public static VisualizarVagaDto copiarDaEntidadeProDto (Vaga entidade){
        VisualizarVagaDto dto = new VisualizarVagaDto();
        dto.id = entidade.getId();
        dto.titulo = entidade.getTitulo();
        dto.descricao = entidade.getDecricao();
        dto.cargo = entidade.getCargo();
        dto.situacao = entidade.getSituacao();
        dto.empresa = VisualizarEmpresaDto.copiarDaEntidadeProDto(entidade.getEmpresa());
        dto.profissionais = entidade.getProfissionais().stream().map(VisualizarProfissionalDto::copiarDaEntidadeProDto).collect(Collectors.toSet());

        return dto;
    }
}