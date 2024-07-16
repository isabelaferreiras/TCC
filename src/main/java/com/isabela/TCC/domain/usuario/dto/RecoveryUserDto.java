package com.isabela.TCC.domain.usuario.dto;

import com.isabela.TCC.domain.usuario.model.Role;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String email,
        List<Role> roles
) {
}
