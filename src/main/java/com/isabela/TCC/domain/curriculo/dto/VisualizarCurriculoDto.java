package com.isabela.TCC.domain.curriculo.dto;

import com.isabela.TCC.domain.curriculo.model.Curriculo;
import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalDto;
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

    public static VisualizarCurriculoDto copiarDaEntidadeProDto (Curriculo entidade){
        VisualizarCurriculoDto dto = new VisualizarCurriculoDto();
        dto.id = entidade.getId();
        dto.escolaridade = entidade.getEscolaridade();
        dto.experienciaProfissionais = entidade.getExperienciasProfissionais();
        dto.habilidadesPessoais = entidade.getHabilidadesPessoais();
        dto.habilidadesTecnicas = entidade.getHabilidadesTecnicas();
        dto.idiomas = entidade.getIdiomas();
        dto.profissional = VisualizarProfissionalDto.copiarDaEntidadeProDto(entidade.getProfissional());

        return dto;
    }
}
