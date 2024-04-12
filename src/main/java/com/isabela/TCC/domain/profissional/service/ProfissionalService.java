package com.isabela.TCC.domain.profissional.service;

import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.profissional.dto.AtualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.CadastrarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.profissional.repository.ProfissionalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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

    @Transactional
    public VisualizarProfissionalDto FindProfissionalById(Long id){
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profissional com o ID: " + id + " não encontrado."));
        return VisualizarProfissionalDto.copiarDaEntidadeProDto(profissional);
    }

    @Transactional
    public Page<VisualizarProfissionalDto> listarProfissionais(int pagina, int qtde){

        Pageable paginacao = PageRequest.of(pagina, qtde);

        Page<Profissional> profissionais = profissionalRepository.findAll(paginacao);
        return profissionais.map(VisualizarProfissionalDto::copiarDaEntidadeProDto);
    }

    @Transactional
    public VisualizarProfissionalDto atualizarDadosProfissional(Long id, AtualizarProfissionalDto dto){
        Profissional profissionalAtualizado = profissionalRepository.getReferenceById(id);
        try {
            profissionalAtualizado.setSenha(dto.getSenha());
            profissionalAtualizado.setNome(dto.getNome());
            profissionalAtualizado.setEndereco(dto.getEndereco());

            profissionalRepository.save(profissionalAtualizado);
            return VisualizarProfissionalDto.copiarDaEntidadeProDto(profissionalAtualizado);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Profissional com o ID: " + id + " não encontrado.");
        }

    }

    @Transactional
    public void deletarProfissional(Long id){
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profissional com o ID: " + id + "não encontrado."));
                profissionalRepository.delete(profissional);
    }

}
