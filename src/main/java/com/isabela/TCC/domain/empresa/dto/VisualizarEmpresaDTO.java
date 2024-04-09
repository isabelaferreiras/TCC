package com.isabela.TCC.domain.empresa.dto;

import com.isabela.TCC.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualizarEmpresaDTO {
    private String email;
    private String nomeEmpresa;
    private String descricao;
    private String ramo;
    private Endereco endereco;
}
