package com.isabela.TCC.domain.empresa.service;

import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.enums.Situacao;
import com.isabela.TCC.domain.empresa.dto.CadastrarEmpresaDTO;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.empresa.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public VisualizarEmpresaDto cadastrarEmpresa(CadastrarEmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setEmail(dto.getEmail());
        empresa.setSenha(dto.getSenha());
        empresa.setNomeEmpresa(dto.getNomeEmpresa());
        empresa.setEndereco(dto.getEndereco());
        empresa.setDescricao(dto.getDescricao());
        empresa.setRamo(dto.getRamo());
        empresa.setCnpj(dto.getCnpj());
        empresa.setSituacao(Situacao.NAO_ATIVO);
        empresa.setCreateAt(LocalDateTime.now());
        empresa.setUpdateAt(LocalDateTime.now());

        empresaRepository.save(empresa);

        return VisualizarEmpresaDto.copiarDaEntidadeProDto(empresa);
    }

    @Transactional
    public VisualizarEmpresaDto FindEmpresaById(Long id){
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa com o ID: " + id + " não encontrado."));
        return VisualizarEmpresaDto.copiarDaEntidadeProDto(empresa);
    }

    @Transactional
    public List<VisualizarEmpresaDto> listarEmpresas(){
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas.stream()
                .map(VisualizarEmpresaDto::copiarDaEntidadeProDto)
                .toList();
    }


}
