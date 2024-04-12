package com.isabela.TCC.domain.vaga.dto;

import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    public static VisualizarVagaDto copiarDaEntidadeProDto (Vaga entidade){
        VisualizarVagaDto dto = new VisualizarVagaDto();
        dto.id = entidade.getId();
        dto.titulo = entidade.getTitulo();
        dto.descricao = entidade.getDecricao();
        dto.cargo = entidade.getCargo();
        dto.situacao = entidade.getSituacao();

        return dto;
    }
}