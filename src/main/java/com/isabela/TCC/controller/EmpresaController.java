package com.isabela.TCC.controller;

import com.isabela.TCC.domain.empresa.dto.CadastrarEmpresaDTO;
import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.empresa.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<VisualizarEmpresaDto> cadastrarEmpresa(@RequestBody @Valid CadastrarEmpresaDTO dto){
        VisualizarEmpresaDto empresa = empresaService.cadastrarEmpresa(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).body(empresa);
    }

    @GetMapping
    public ResponseEntity<List<VisualizarEmpresaDto>> verEmpresas(){
        List<VisualizarEmpresaDto> empresa = empresaService.listarEmpresas();
        return ResponseEntity.ok(empresa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarEmpresaDto> findEmpresaById(@PathVariable("id") Long id){
        VisualizarEmpresaDto empresa = empresaService.FindEmpresaById(id);
        return ResponseEntity.ok(empresa);
    }
}
