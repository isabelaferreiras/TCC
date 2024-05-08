package com.isabela.TCC.domain.empresa.dto;

import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.vaga.dto.VisualizarVagaProfissionalDto;
import com.isabela.TCC.utils.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualizarEmpresaDaVagaDto {

    private String nomeEmpresa;
    private Endereco endereco;
    private String descricao;
    private String ramo;

    public static VisualizarEmpresaDaVagaDto copiarDaEntidadeProDto (Empresa entidade){
        VisualizarEmpresaDaVagaDto dto = new VisualizarEmpresaDaVagaDto();
        dto.nomeEmpresa = entidade.getNomeEmpresa();
        dto.endereco = entidade.getEndereco();
        dto.descricao = entidade.getDescricao();
        dto.ramo = entidade.getRamo();

        return dto;
    }
}
