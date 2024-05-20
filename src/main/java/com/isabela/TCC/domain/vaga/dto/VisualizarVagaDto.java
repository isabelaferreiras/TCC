package com.isabela.TCC.domain.vaga.dto;

import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDaVagaDto;
import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalVagaDto;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.enums.Situacao;
import com.isabela.TCC.utils.Endereco;
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
    private VisualizarEmpresaDaVagaDto empresa;
    private Endereco endereco;
    private String descricao;
    private String cargo;
    private Situacao situacao = Situacao.NAO_ATIVO;
    private Set<VisualizarProfissionalVagaDto> profissionais;

    public static VisualizarVagaDto copiarDaEntidadeProDto (Vaga entidade){
        VisualizarVagaDto dto = new VisualizarVagaDto();
        dto.id = entidade.getId();
        dto.titulo = entidade.getTitulo();
        dto.descricao = entidade.getDecricao();
        dto.endereco = entidade.getEndereco();
        dto.cargo = entidade.getCargo();
        dto.situacao = entidade.getSituacao();
        dto.empresa = VisualizarEmpresaDaVagaDto.copiarDaEntidadeProDto(entidade.getEmpresa());
        dto.profissionais = entidade.getProfissionais().stream().map(VisualizarProfissionalVagaDto::copiarDaEntidadeProDto).collect(Collectors.toSet());

        return dto;
    }
}