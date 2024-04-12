package com.isabela.TCC.controller;


import com.isabela.TCC.domain.curriculo.repository.CurriculoRepository;
import com.isabela.TCC.domain.profissional.dto.CadastrarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.profissional.service.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    private CurriculoRepository curriculoRepository;

    @PostMapping
    public ResponseEntity<VisualizarProfissionalDto> cadastrarProfissional(@RequestBody @Valid CadastrarProfissionalDto dto){
        VisualizarProfissionalDto profissional = profissionalService.cadastrarProfissional(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(profissional.getId()).toUri();
        return ResponseEntity.created(uri).body(profissional);
    }

}
