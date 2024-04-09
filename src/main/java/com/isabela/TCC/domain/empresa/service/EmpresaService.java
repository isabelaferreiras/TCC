package com.isabela.TCC.domain.empresa.service;

import com.isabela.TCC.enums.Situacao;
import com.isabela.TCC.domain.empresa.dto.CadastrarEmpresaDTO;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.empresa.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    @Transactional
    public Empresa cadastrarEmpresa(CadastrarEmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setEmail(dto.getEmail());
        empresa.setSenha(dto.getSenha());
        empresa.setNomeEmpresa(dto.getNomeEmpresa());
        empresa.setEndereco(dto.getEndereco());
        empresa.setDescricao(dto.getDescricao());
        empresa.setRamo(dto.getRamo());
        empresa.setSituacao(Situacao.NAO_ATIVO);
        empresa.setCreateAt(LocalDateTime.now());
        empresa.setUpdateAt(LocalDateTime.now());

        return repository.save(empresa);
    }
}
