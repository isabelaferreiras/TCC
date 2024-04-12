package com.isabela.TCC.domain.vaga.service;

import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.empresa.repository.EmpresaRepository;
import com.isabela.TCC.domain.vaga.dto.CadastrarVagaDto;
import com.isabela.TCC.domain.vaga.dto.VisualizarVagaDto;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.domain.vaga.repository.VagaRepository;
import com.isabela.TCC.enums.Situacao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public VisualizarVagaDto cadastrarVaga(CadastrarVagaDto dto){
        Vaga vaga = new Vaga();
        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa com o ID: " + dto.getEmpresaId() + " não encontrado."));
        vaga.setTitulo(dto.getTitulo());
        vaga.setDecricao(dto.getDescricao());
        vaga.setCargo(dto.getCargo());
        vaga.setSituacao(Situacao.NAO_ATIVO);

        vagaRepository.save(vaga);
        return VisualizarVagaDto.copiarDaEntidadeProDto(vaga);
    }

}
