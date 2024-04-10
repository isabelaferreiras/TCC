package com.isabela.TCC.domain.profissional.repository;

import com.isabela.TCC.domain.profissional.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
