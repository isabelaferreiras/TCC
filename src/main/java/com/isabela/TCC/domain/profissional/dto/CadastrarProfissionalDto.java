package com.isabela.TCC.domain.profissional.dto;

import com.isabela.TCC.domain.curriculo.model.Curriculo;
import com.isabela.TCC.domain.empresa.dto.CadastrarEmpresaDTO;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.utils.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarProfissionalDto {

    @Email
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    @Length(min = 12)
    private String senha;
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    private String dataNascimento;
    private Endereco endereco;

    public static CadastrarProfissionalDto copiarDaEntidadeProDto(Profissional entidade){
        CadastrarProfissionalDto dto = new CadastrarProfissionalDto();
        dto.nome = entidade.getNome();
        dto.email = entidade.getEmail();
        dto.senha = entidade.getSenha();
        dto.endereco = entidade.getEndereco();
        dto.dataNascimento = String.valueOf(entidade.getDataNascimento());

        return dto;
    }
}
