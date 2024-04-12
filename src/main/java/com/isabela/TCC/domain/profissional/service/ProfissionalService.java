package com.isabela.TCC.domain.profissional.service;

import com.isabela.TCC.domain.profissional.dto.CadastrarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.profissional.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Transactional
    public VisualizarProfissionalDto cadastrarProfissional(CadastrarProfissionalDto dto){
        Profissional profissional = new Profissional();
        profissional.setEmail(dto.getEmail());
        profissional.setSenha(dto.getSenha());
        profissional.setNome(dto.getNome());
        profissional.setDataNascimento(LocalDate.parse(dto.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        profissional.setEndereco(dto.getEndereco());
        profissional.setCreateAt(LocalDateTime.now());
        profissional.setUpdateAt(LocalDateTime.now());

        profissionalRepository.save(profissional);

        return VisualizarProfissionalDto.copiarDaEntidadeProDto(profissional);
    }

}
