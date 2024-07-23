package com.isabela.TCC.domain.curriculo.repository;

import com.isabela.TCC.domain.curriculo.model.Curriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
    Curriculo findByProfissionalId(Long profissionalId);
}
