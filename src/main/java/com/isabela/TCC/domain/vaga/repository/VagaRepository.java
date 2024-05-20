package com.isabela.TCC.domain.vaga.repository;

import com.isabela.TCC.domain.vaga.model.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    Page<Vaga> findByTituloAndCargoAndEndereco(String titulo, String cargo, String endereco);

    @Query("SELECT v FROM Vaga v WHERE v.titulo = :titulo AND v.cargo = :cargo AND v.endereco = :endereco")
    Page<Vaga> encontrarPorFiltros(@Param("titulo") String titulo, @Param("cargo") String cargo, @Param("endereco") String endereco);



}
