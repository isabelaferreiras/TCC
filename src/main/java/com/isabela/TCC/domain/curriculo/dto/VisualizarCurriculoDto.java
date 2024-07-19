package com.isabela.TCC.domain.curriculo.dto;

import com.isabela.TCC.domain.curriculo.model.Curriculo;
import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.vaga.dto.VisualizarVagaDto;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.enums.Escolaridade;
import com.isabela.TCC.utils.ExperienciaProfissional;
import com.isabela.TCC.utils.HabilidadePessoal;
import com.isabela.TCC.utils.HabilidadeTecnica;
import com.isabela.TCC.utils.Idioma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VisualizarCurriculoDto {

    private Long id;
    private VisualizarProfissionalDto profissional;
    private Escolaridade escolaridade = Escolaridade.ENSINO_FUNDAMENTAL_INCOMPLETO;
    private Set<ExperienciaProfissional> experienciaProfissionais = new HashSet<>();
    private Set<HabilidadePessoal> habilidadesPessoais = new HashSet<>();
    private Set<HabilidadeTecnica> habilidadesTecnicas = new HashSet<>();
    private Set<Idioma> idiomas = new HashSet<>();

    public static VisualizarCurriculoDto copiarDaEntidadeProDto (Curriculo entidadeCurriculo){
        VisualizarCurriculoDto dto = new VisualizarCurriculoDto();
        dto.id = entidadeCurriculo.getId();
        dto.escolaridade = entidadeCurriculo.getEscolaridade();
        dto.experienciaProfissionais = entidadeCurriculo.getExperienciasProfissionais();
        dto.habilidadesPessoais = entidadeCurriculo.getHabilidadesPessoais();
        dto.habilidadesTecnicas = entidadeCurriculo.getHabilidadesTecnicas();
        dto.idiomas = entidadeCurriculo.getIdiomas();
        dto.profissional = VisualizarProfissionalDto.copiarDaEntidadeProDto(entidadeCurriculo.getProfissional());

        return dto;
    }
}
