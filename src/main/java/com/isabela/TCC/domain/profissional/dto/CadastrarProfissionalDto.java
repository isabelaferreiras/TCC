package com.isabela.TCC.domain.profissional.dto;

import com.isabela.TCC.domain.curriculo.model.Curriculo;
import com.isabela.TCC.domain.empresa.dto.CadastrarEmpresaDTO;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.utils.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.format.DateTimeFormatter;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarProfissionalDto {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotBlank
    private String nome;
    @NotBlank
    private String dataNascimento;
    private Endereco endereco;

    public static CadastrarProfissionalDto copiarDaEntidadeProDto(Profissional entidade){
        CadastrarProfissionalDto dto = new CadastrarProfissionalDto();
        dto.nome = entidade.getNome();
        dto.email = entidade.getEmail();
        dto.senha = entidade.getSenha();
        dto.endereco = entidade.getEndereco();
        dto.dataNascimento = entidade.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return dto;
    }
}
