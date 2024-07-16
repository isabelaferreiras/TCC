package com.isabela.TCC.domain.profissional.dto;

import com.isabela.TCC.domain.empresa.dto.AtualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.utils.Endereco;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AtualizarProfissionalDto {

    @NotNull(message = "Nome não pode ser nulo.")
    @NotEmpty(message = "Nome não pode estar vazio.")
    private String nome;
    private Endereco endereco;
    private LocalDateTime updateAt;

    public static AtualizarProfissionalDto copiarDaEntidadeProDto (Profissional entidade){
        AtualizarProfissionalDto dto = new AtualizarProfissionalDto();
        dto.nome = entidade.getNome();
        dto.endereco = entidade.getEndereco();

        return dto;
    }
}
