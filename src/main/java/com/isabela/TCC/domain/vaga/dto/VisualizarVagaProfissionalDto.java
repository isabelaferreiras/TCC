package com.isabela.TCC.domain.vaga.dto;

import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDaVagaDto;
import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalVagaDto;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.enums.Situacao;
import com.isabela.TCC.utils.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualizarVagaProfissionalDto {

    private Long id;
    private String titulo;
    private VisualizarEmpresaDaVagaDto empresa;
    private Endereco endereco;
    private String descricao;
    private String cargo;
    private Situacao situacao;

    public static VisualizarVagaProfissionalDto copiarDaEntidadeProDto (Vaga entidade){
        VisualizarVagaProfissionalDto dto = new VisualizarVagaProfissionalDto();

        dto.id = entidade.getId();
        dto.titulo = entidade.getTitulo();
        dto.descricao = entidade.getDecricao();
        dto.cargo = entidade.getCargo();
        dto.endereco = entidade.getEndereco();
        dto.situacao = entidade.getSituacao();
        dto.empresa = VisualizarEmpresaDaVagaDto.copiarDaEntidadeProDto(entidade.getEmpresa());

        return dto;
    }
}
