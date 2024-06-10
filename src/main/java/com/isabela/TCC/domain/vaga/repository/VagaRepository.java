package com.isabela.TCC.domain.vaga.repository;

import com.isabela.TCC.domain.vaga.model.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    @Query("SELECT v FROM Vaga v WHERE (LOWER(v.titulo) IS NULL OR LOWER(v.titulo) LIKE %:titulo%) AND (LOWER(v.cargo) IS NULL OR LOWER(v.cargo) LIKE %:cargo%)")
    Page<Vaga> encontrarPorFiltros(@Param("titulo") String titulo, @Param("cargo") String cargo, PageRequest pageRequest);



}
