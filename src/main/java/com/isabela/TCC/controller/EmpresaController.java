package com.isabela.TCC.controller;

import com.isabela.TCC.domain.empresa.dto.CadastrarEmpresaDTO;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.empresa.service.EmpresaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService service;

    @PostMapping
    public ResponseEntity<Empresa> cadastrarEmpresa(@RequestBody CadastrarEmpresaDTO dto){
        Empresa empresa = service.cadastrarEmpresa(dto);
        return ResponseEntity.ok(empresa);
    }
}
