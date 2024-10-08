package com.isabela.TCC.controller;

import com.isabela.TCC.domain.vaga.dto.AtualizarVagaDto;
import com.isabela.TCC.domain.vaga.dto.CadastrarVagaDto;
import com.isabela.TCC.domain.vaga.dto.VisualizarVagaComFiltroDto;
import com.isabela.TCC.domain.vaga.dto.VisualizarVagaDto;
import com.isabela.TCC.domain.vaga.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/vagas")
@CrossOrigin("*")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @PostMapping
    public ResponseEntity<VisualizarVagaDto> cadastrarVaga(@RequestBody @Valid CadastrarVagaDto dto){
        VisualizarVagaDto vaga = vagaService.cadastrarVaga(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(vaga.getId()).toUri();
        return ResponseEntity.created(uri).body(vaga);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarVagaDto> findVagaById(@PathVariable("id") Long id){
        VisualizarVagaDto vaga = vagaService.findVagaById(id);
        return ResponseEntity.ok(vaga);
    }

    @GetMapping
    public ResponseEntity<Page<VisualizarVagaDto>> listarVagas(@RequestParam int pagina,
                                                               @RequestParam int qtde){
        Page<VisualizarVagaDto> vagas = vagaService.listarVagas(pagina, qtde);
        return ResponseEntity.ok(vagas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisualizarVagaDto> atualizarVaga(@PathVariable("id") Long id,
                                                           @RequestBody @Valid AtualizarVagaDto dto){
        VisualizarVagaDto vaga = vagaService.atualizarVaga(id, dto);
        return ResponseEntity.ok(vaga);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable("id") Long id){
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vaga-com-filtro")
    public ResponseEntity<?> mostrarVagasComFiltros(@RequestParam(required = false) String titulo,
                                                    @RequestParam(required = false) String cargo,
                                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "40") Integer linesPerPage,
                                                    @RequestParam(value = "direction", defaultValue = "DESC") String direction,
                                                    @RequestParam(value = "orderBy", defaultValue = "id") String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<VisualizarVagaComFiltroDto> exibirVagasComFiltro = vagaService.exibirVagasComFiltro(titulo, cargo, pageRequest);
        return ResponseEntity.ok(exibirVagasComFiltro);
    }

}
