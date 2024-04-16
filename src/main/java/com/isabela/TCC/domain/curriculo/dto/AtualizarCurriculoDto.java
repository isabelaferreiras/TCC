package com.isabela.TCC.domain.curriculo.dto;

import com.isabela.TCC.enums.Escolaridade;
import com.isabela.TCC.utils.ExperienciaProfissional;
import com.isabela.TCC.utils.HabilidadePessoal;
import com.isabela.TCC.utils.HabilidadeTecnica;
import com.isabela.TCC.utils.Idioma;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AtualizarCurriculoDto {

    private Escolaridade escolaridade;
    private Set<ExperienciaProfissional> experienciasProfissionais = new HashSet<>();
    private Set<HabilidadePessoal> habilidadesPessoais = new HashSet<>();
    private Set<HabilidadeTecnica> habilidadesTecnicas = new HashSet<>();
    private Set<Idioma> idiomas = new HashSet<>();
    private LocalDateTime updateAt;
}
