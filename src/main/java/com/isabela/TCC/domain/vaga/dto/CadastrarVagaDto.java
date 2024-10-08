package com.isabela.TCC.domain.vaga.dto;

import com.isabela.TCC.domain.empresa.dto.CadastrarEmpresaDTO;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.enums.Situacao;
import com.isabela.TCC.utils.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CadastrarVagaDto {

    @NotBlank
    @Size(min = 5, max = 20)
    private String titulo;
    private Long empresaId;
    private Endereco endereco;
    @NotBlank
    @Size(min = 10, max = 400)
    private String descricao;
    @NotBlank
    @Size(min = 4, max = 20)
    private String cargo;
    private Boolean limite;
    private Integer limiteProfissionais;

}
