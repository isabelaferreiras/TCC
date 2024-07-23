package com.isabela.TCC.domain.curriculo.service;

import com.isabela.TCC.domain.curriculo.dto.AtualizarCurriculoDto;
import com.isabela.TCC.domain.curriculo.dto.CadastrarCurriculoDto;
import com.isabela.TCC.domain.curriculo.dto.VisualizarCurriculoDto;
import com.isabela.TCC.domain.curriculo.model.Curriculo;
import com.isabela.TCC.domain.curriculo.repository.CurriculoRepository;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.profissional.repository.ProfissionalRepository;
import com.isabela.TCC.utils.ExperienciaProfissional;
import com.isabela.TCC.utils.HabilidadePessoal;
import com.isabela.TCC.utils.HabilidadeTecnica;
import com.isabela.TCC.utils.Idioma;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;
    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Transactional
    public Curriculo getCurriculoByProfissionalId(Long profissionalId) {
        return curriculoRepository.findByProfissionalId(profissionalId);
    }

    @Transactional
    public VisualizarCurriculoDto cadastrarCurriculo(CadastrarCurriculoDto dto){
        Curriculo curriculo = new Curriculo();
        Profissional profissional = profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new EntityNotFoundException("Profissional com o ID: " + dto.getProfissionalId() + " não encontrado."));
        curriculo.setEscolaridade(dto.getEscolaridade());
        curriculo.setExperienciasProfissionais(dto.getExperienciasProfissionais());
        curriculo.setHabilidadesPessoais(dto.getHabilidadesPessoais());
        curriculo.setHabilidadesTecnicas(dto.getHabilidadesTecnicas());
        curriculo.setIdiomas(dto.getIdiomas());
        curriculo.setProfissional(profissional);
        curriculo.setCreateAt(LocalDateTime.now());
        curriculo.setUpdateAt(LocalDateTime.now());

        curriculoRepository.save(curriculo);
        return VisualizarCurriculoDto.copiarDaEntidadeProDto(curriculo);
    }

    @Transactional
    public VisualizarCurriculoDto findCurriculoById(Long id){
        Curriculo curriculo = curriculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Currículo com o ID: " + id + " não encontrado."));
        return VisualizarCurriculoDto.copiarDaEntidadeProDto(curriculo);
    }


    @Transactional
    public VisualizarCurriculoDto atualizarCurriculo(Long id, AtualizarCurriculoDto dto){
        Curriculo curriculoAtualizado = curriculoRepository.getReferenceById(id);
        try {
            curriculoAtualizado.setEscolaridade(dto.getEscolaridade());
            curriculoAtualizado.setUpdateAt(LocalDateTime.now());

            curriculoRepository.save(curriculoAtualizado);
            return VisualizarCurriculoDto.copiarDaEntidadeProDto(curriculoAtualizado);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Currículo com o ID: " + id + " não encontrado.");
        }
    }

    @Transactional
    public void deletarCurriculo(Long id){
        Curriculo curriculo = curriculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Currículo com o ID: " + id + " não encontrado."));
        curriculoRepository.delete(curriculo);
    }

    @Transactional
    public void adicionarExperienciaProfissional(Long curriculoId, ExperienciaProfissional experienciaProfissional){
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));
        curriculo.getExperienciasProfissionais().add(experienciaProfissional);
        curriculoRepository.save(curriculo);
    }

    @Transactional
    public void atualizarExperienciaProfissional(Long curriculoId, ExperienciaProfissional experienciaProfissional) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        if (curriculo.getExperienciasProfissionais().contains(experienciaProfissional)) {
            curriculo.getExperienciasProfissionais().remove(experienciaProfissional);
            curriculo.getExperienciasProfissionais().add(experienciaProfissional);
            curriculoRepository.save(curriculo);
        } else {
            throw new IllegalArgumentException("Experiência profissional não encontrada no currículo");
        }
    }

    @Transactional
    public void deletarExperienciaProfissional(Long curriculoId, ExperienciaProfissional experienciaProfissional) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        curriculo.getExperienciasProfissionais().remove(experienciaProfissional);
        curriculoRepository.save(curriculo);
    }


    @Transactional
    public void adicionarHabilidadePessoal(Long curriculoId, HabilidadePessoal habilidadePessoal) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        curriculo.getHabilidadesPessoais().add(habilidadePessoal);
        curriculoRepository.save(curriculo);
    }

    @Transactional
    public void atualizarHabilidadePessoal(Long curriculoId, HabilidadePessoal habilidadePessoal) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        if (curriculo.getHabilidadesPessoais().contains(habilidadePessoal)) {
            curriculo.getHabilidadesPessoais().remove(habilidadePessoal);
            curriculo.getHabilidadesPessoais().add(habilidadePessoal);
            curriculoRepository.save(curriculo);
        } else {
            throw new IllegalArgumentException("Habilidade pessoal não encontrada no currículo");
        }
    }

    @Transactional
    public void deletarHabilidadePessoal(Long curriculoId, HabilidadePessoal habilidadePessoal) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        curriculo.getHabilidadesPessoais().remove(habilidadePessoal);
        curriculoRepository.save(curriculo);
    }

    @Transactional
    public void adicionarHabilidadeTecnica(Long curriculoId, HabilidadeTecnica habilidadeTecnica) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        curriculo.getHabilidadesTecnicas().add(habilidadeTecnica);
        curriculoRepository.save(curriculo);
    }

    @Transactional
    public void atualizarHabilidadeTecnica(Long curriculoId, HabilidadeTecnica habilidadeTecnica) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        if (curriculo.getHabilidadesTecnicas().contains(habilidadeTecnica)) {
            curriculo.getHabilidadesTecnicas().remove(habilidadeTecnica);
            curriculo.getHabilidadesTecnicas().add(habilidadeTecnica);
            curriculoRepository.save(curriculo);
        } else {
            throw new IllegalArgumentException("Habilidade técnica não encontrada no currículo");
        }
    }

    @Transactional
    public void deletarHabilidadeTecnica(Long curriculoId, HabilidadeTecnica habilidadeTecnica) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        curriculo.getHabilidadesTecnicas().remove(habilidadeTecnica);
        curriculoRepository.save(curriculo);
    }

    @Transactional
    public void adicionarIdioma(Long curriculoId, Idioma idioma) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        curriculo.getIdiomas().add(idioma);
        curriculoRepository.save(curriculo);
    }

    @Transactional
    public void atualizarIdioma(Long curriculoId, Idioma idioma) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        if (curriculo.getIdiomas().contains(idioma)) {
            curriculo.getIdiomas().remove(idioma);
            curriculo.getIdiomas().add(idioma);
            curriculoRepository.save(curriculo);
        } else {
            throw new IllegalArgumentException("Idioma não encontrado no currículo");
        }
    }

    @Transactional
    public void deletarIdioma(Long curriculoId, Idioma idioma) {
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado com ID: " + curriculoId));

        curriculo.getIdiomas().remove(idioma);
        curriculoRepository.save(curriculo);
    }
}
