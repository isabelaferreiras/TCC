package com.isabela.TCC.domain.empresa.dto;

import com.isabela.TCC.domain.Endereco;
import com.isabela.TCC.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarEmpresaDTO {
    private String email;
    private String senha;
    private String nomeEmpresa;

    private Endereco endereco;
    private String descricao;
    private String ramo;
    private Situacao situacao = Situacao.NAO_ATIVO;

}
