package com.isabela.TCC.domain.usuario;

import com.isabela.TCC.domain.usuario.role.UserRole;

public record LoginResponseDto(String token, Long empresaId, Long profissionalId, UserRole role) {
}
