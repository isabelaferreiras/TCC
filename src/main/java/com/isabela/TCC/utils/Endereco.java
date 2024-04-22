package com.isabela.TCC.utils;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {

    @NotNull(message = "Cidade não pode ser nulo.")
    @NotEmpty(message = "Cidade não pode estar vazio.")
    private String cidade;
    @NotNull(message = "Estado não pode ser nulo.")
    @NotEmpty(message = "Estado não pode estar vazio.")
    @Size(min = 2, max = 2)
    private String estado;
}
