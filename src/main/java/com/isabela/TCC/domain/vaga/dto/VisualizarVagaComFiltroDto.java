package com.isabela.TCC.domain.vaga.dto;

import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDaVagaDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalVagaDto;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.utils.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualizarVagaComFiltroDto {

    private String titulo;
    private VisualizarEmpresaDaVagaDto empresa;
    private String descricao;
    private String cargo;

    public static VisualizarVagaComFiltroDto copiarDaEntidadeProDto (Vaga entidade){
        VisualizarVagaComFiltroDto dto = new VisualizarVagaComFiltroDto();
        dto.titulo = entidade.getTitulo();
        dto.descricao = entidade.getDecricao();
        dto.cargo = entidade.getCargo();
        dto.empresa = VisualizarEmpresaDaVagaDto.copiarDaEntidadeProDto(entidade.getEmpresa());

        return dto;
    }
}
