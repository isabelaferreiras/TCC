package com.isabela.TCC.domain.usuario.dto;

import com.isabela.TCC.domain.usuario.role.RoleName;

public record CreateUserDto(
        String email,
        String password,
        RoleName role
) {
}
