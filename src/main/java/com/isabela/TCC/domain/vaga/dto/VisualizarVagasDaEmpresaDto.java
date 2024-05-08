package com.isabela.TCC.domain.vaga.dto;

import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualizarVagasDaEmpresaDto {
    private String titulo;
    private String descricao;
    private String cargo;
    private Situacao situacao = Situacao.NAO_ATIVO;

    public static VisualizarVagasDaEmpresaDto copiarDaEntidadeProDto (Vaga entidade){
        VisualizarVagasDaEmpresaDto dto = new VisualizarVagasDaEmpresaDto();
        dto.titulo = entidade.getTitulo();
        dto.descricao = entidade.getDecricao();
        dto.cargo = entidade.getCargo();
        dto.situacao = entidade.getSituacao();

        return dto;
    }
}
