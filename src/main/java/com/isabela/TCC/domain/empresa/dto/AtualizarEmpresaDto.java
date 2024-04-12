package com.isabela.TCC.domain.empresa.dto;

import com.isabela.TCC.utils.Endereco;
import com.isabela.TCC.domain.empresa.model.Empresa;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    private String senha;
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String nomeEmpresa;
    private Endereco endereco;
    private String descricao;
    private String ramo;
    private LocalDateTime updateAt;

    public static AtualizarEmpresaDto copiarDaEntidadeProDto (Empresa entidade){
        AtualizarEmpresaDto dto = new AtualizarEmpresaDto();
        dto.nomeEmpresa = entidade.getNomeEmpresa();
        dto.senha = entidade.getSenha();
        dto.descricao = entidade.getDescricao();
        dto.ramo = entidade.getRamo();
        dto.endereco = entidade.getEndereco();

        return dto;
    }

}
