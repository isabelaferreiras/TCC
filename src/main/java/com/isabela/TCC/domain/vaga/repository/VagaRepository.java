package com.isabela.TCC.domain.vaga.repository;

import com.isabela.TCC.domain.vaga.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
}