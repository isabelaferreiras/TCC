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
    @NotNull(message = "Senha não pode ser nula.")
    @NotEmpty(message = "Senha não pode estar vazia.")
    private String senha;
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
    private Situacao situacao = Situacao.NAO_ATIVO;

    public static CadastrarEmpresaDTO copiarDaEntidadeProDto (Empresa entidade){
        CadastrarEmpresaDTO dto = new CadastrarEmpresaDTO();
        dto.nomeEmpresa = entidade.getNomeEmpresa();
        dto.email = entidade.getEmail();
        dto.senha = entidade.getSenha();
        dto.descricao = entidade.getDescricao();
        dto.ramo = entidade.getRamo();
        dto.endereco = entidade.getEndereco();
        dto.situacao = entidade.getSituacao();
        dto.cnpj = entidade.getCnpj();
        return dto;
    }

}
