package com.isabela.TCC.domain.empresa.repository;

import com.isabela.TCC.domain.empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
