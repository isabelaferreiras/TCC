package com.isabela.TCC.domain.usuario.dto;

import com.isabela.TCC.domain.usuario.role.UserRole;

public record RegisterDto(String login, String password, UserRole role) {
}
