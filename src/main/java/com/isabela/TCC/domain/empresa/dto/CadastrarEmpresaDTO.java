package com.isabela.TCC.domain.empresa.dto;

import com.isabela.TCC.utils.Endereco;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.enums.Situacao;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarEmpresaDTO {

    @NotNull(message = "E-mail não pode ser nulo.")
    @NotEmpty(message = "E-mail não pode estar vazio.")
    @Email
    private String email;
    @NotNull(message = "Nome não pode ser nulo.")
    @NotEmpty(message = "Nome não pode estar vazio.")
    @Size(min = 3, max = 30)
    private String nomeEmpresa;
    private Endereco endereco;
    @NotNull
    @NotEmpty
    private String descricao;
    @NotBlank(message = "Deve conter CNPJ.")
    private String cnpj;
    @NotNull(message = "Ramo não pode ser nulo.")
    @NotEmpty(message = "Ramo não pode ser nulo.")
    @Size(min = 4, max = 20)
    private String ramo;
    private String password;


}
