package com.isabela.TCC.controller;

import com.isabela.TCC.domain.empresa.dto.CadastrarEmpresaDTO;
import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Empresa> cadastrarEmpresa(@RequestBody CadastrarEmpresaDTO dto){
        Empresa empresa = empresaService.cadastrarEmpresa(dto);
        return ResponseEntity.ok(empresa);
    }

    @GetMapping
    public ResponseEntity<VisualizarEmpresaDto> verEmpresas(){
        Empresa empresa = empresaService.
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarEmpresaDto> findEmpresaById(@PathVariable("id") Long id){
        VisualizarEmpresaDto empresa = empresaService.FindEmpresaById(id);
        return ResponseEntity.ok(empresa);
    }
}
