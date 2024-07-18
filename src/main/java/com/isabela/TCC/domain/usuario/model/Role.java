package com.isabela.TCC.domain.usuario.model;

import com.isabela.TCC.domain.usuario.role.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tb_roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;
}
