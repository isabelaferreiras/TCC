package com.isabela.TCC.controller;

import com.isabela.TCC.domain.curriculo.dto.AtualizarCurriculoDto;
import com.isabela.TCC.domain.curriculo.dto.CadastrarCurriculoDto;
import com.isabela.TCC.domain.curriculo.dto.VisualizarCurriculoDto;
import com.isabela.TCC.domain.curriculo.service.CurriculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/curriculo")
public class CurriculoController {

    @Autowired
    private CurriculoService curriculoService;

    @PostMapping
    public ResponseEntity<VisualizarCurriculoDto> cadastrarCurriculo(@RequestBody @Valid CadastrarCurriculoDto dto){
        VisualizarCurriculoDto curriculo = curriculoService.cadastrarCurriculo(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(curriculo.getId()).toUri();
        return ResponseEntity.created(uri).body(curriculo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarCurriculoDto> findCurriculoById(@PathVariable("id") Long id){
        VisualizarCurriculoDto curriculo = curriculoService.findCurriculoById(id);
        return ResponseEntity.ok(curriculo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<VisualizarCurriculoDto> atualizarCurriculo(@PathVariable("id") Long id, AtualizarCurriculoDto dto){
        VisualizarCurriculoDto curriculo = curriculoService.atualizarCurriculo(id, dto);
        return ResponseEntity.ok(curriculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable("id") Long id){
        curriculoService.deletarCurriculo(id);
        return ResponseEntity.noContent().build();
    }
}
