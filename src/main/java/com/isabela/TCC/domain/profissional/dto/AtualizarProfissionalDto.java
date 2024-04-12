package com.isabela.TCC.domain.profissional.dto;

import com.isabela.TCC.domain.empresa.dto.AtualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.utils.Endereco;
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

    private String senha;
    private String nome;
    private Endereco endereco;
    private LocalDateTime updateAt;

    public static AtualizarProfissionalDto copiarDaEntidadeProDto (Profissional entidade){
        AtualizarProfissionalDto dto = new AtualizarProfissionalDto();
        dto.nome = entidade.getNome();
        dto.senha = entidade.getSenha();
        dto.endereco = entidade.getEndereco();

        return dto;
    }
}
