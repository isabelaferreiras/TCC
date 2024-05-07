package com.isabela.TCC.controller;


import com.isabela.TCC.domain.curriculo.repository.CurriculoRepository;
import com.isabela.TCC.domain.profissional.dto.AtualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.CadastrarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.profissional.service.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarProfissionalDto> findProfissionalById(@PathVariable("id") Long id){
        VisualizarProfissionalDto profissional = profissionalService.FindProfissionalById(id);
        return ResponseEntity.ok(profissional);
    }

    @GetMapping
    public ResponseEntity<Page<VisualizarProfissionalDto>> listarProfissionais(@RequestParam int pagina,
                                                                               @RequestParam int qtde){
        Page<VisualizarProfissionalDto> profissionais = profissionalService.listarProfissionais(pagina, qtde);
        return ResponseEntity.ok(profissionais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisualizarProfissionalDto> atualizarDadosProfissional(@PathVariable("id") Long id,
                                                                                @RequestBody @Valid AtualizarProfissionalDto dto){
        VisualizarProfissionalDto profissional = profissionalService.atualizarDadosProfissional(id, dto);
        return ResponseEntity.ok(profissional);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfissional(@PathVariable("id") Long id){
        profissionalService.deletarProfissional(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cadastroNaVaga")
    public ResponseEntity<Void> cadastrarProfissionalNaVaga(Long profissionalId, Long vagaId){
        profissionalService.cadastrarProfissionalNaVaga(profissionalId, vagaId);
        return ResponseEntity.noContent().build();
    }

}
