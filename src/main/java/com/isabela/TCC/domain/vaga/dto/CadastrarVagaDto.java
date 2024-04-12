package com.isabela.TCC.domain.vaga.dto;

import com.isabela.TCC.domain.empresa.dto.CadastrarEmpresaDTO;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CadastrarVagaDto {

    private String titulo;
    private Long empresaId;
    private String descricao;
    private String cargo;
    private Situacao situacao = Situacao.NAO_ATIVO;

}
