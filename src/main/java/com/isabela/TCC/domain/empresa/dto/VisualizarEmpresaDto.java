package com.isabela.TCC.domain.empresa.dto;

import com.isabela.TCC.domain.Endereco;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.enums.Situacao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualizarEmpresaDto {
    private Long id;
    @NotNull(message = "E-mail não pode ser nulo")
    @NotEmpty(message = "E-mail não pode estar vazio")
    @Email
    private String email;
    @NotNull(message = "Senha não pode ser nula")
    @NotEmpty(message = "Senha não pode estar vazia")
    private String senha;
    @NotNull(message = "Nome não pode ser nulo")
    @NotEmpty(message = "Nome não pode estar vazio")
    @Size(min = 3, max = 30)
    private String nomeEmpresa;
    @NotNull
    @NotEmpty
    private String descricao;
    @NotNull
    @NotEmpty
    private String ramo;

    private Endereco endereco;
    @NotNull
    @NotEmpty
    private String cnpj;
    private Situacao situacao = Situacao.NAO_ATIVO;

    public static VisualizarEmpresaDto copiarDaEntidadeProDto (Empresa entidade){
        VisualizarEmpresaDto dto = new VisualizarEmpresaDto();
        dto.id = entidade.getId();
        dto.nomeEmpresa = entidade.getNomeEmpresa();
        dto.email = entidade.getEmail();
        dto.descricao = entidade.getDescricao();
        dto.ramo = entidade.getRamo();
        dto.endereco = entidade.getEndereco();
        dto.cnpj = entidade.getCnpj();
        dto.situacao = entidade.getSituacao();

        return dto;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualizarEmpresaDto that = (VisualizarEmpresaDto) o;
        return nomeEmpresa.equals(that.nomeEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeEmpresa);
    }
}
