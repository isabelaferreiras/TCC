package com.isabela.TCC.domain.empresa.dto;

import com.isabela.TCC.utils.Endereco;
import com.isabela.TCC.domain.empresa.model.Empresa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AtualizarEmpresaDto {
    @NotNull(message = "Nome da empresa não pode ser nulo")
    @NotEmpty(message = "Nome da empresa não pode estar vazio")
    @Length(min = 2)
    private String nomeEmpresa;
    private Endereco endereco;
    @Size(min = 10, max = 200)
    private String descricao;
    @Size(min = 4, max = 20)
    private String ramo;
    private LocalDateTime updateAt;

    public static AtualizarEmpresaDto copiarDaEntidadeProDto (Empresa entidade){
        AtualizarEmpresaDto dto = new AtualizarEmpresaDto();
        dto.nomeEmpresa = entidade.getNomeEmpresa();
        dto.descricao = entidade.getDescricao();
        dto.ramo = entidade.getRamo();
        dto.endereco = entidade.getEndereco();

        return dto;
    }

}
