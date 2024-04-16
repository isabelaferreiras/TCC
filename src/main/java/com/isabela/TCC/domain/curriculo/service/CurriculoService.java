package com.isabela.TCC.domain.curriculo.service;

import com.isabela.TCC.domain.curriculo.dto.AtualizarCurriculoDto;
import com.isabela.TCC.domain.curriculo.dto.CadastrarCurriculoDto;
import com.isabela.TCC.domain.curriculo.dto.VisualizarCurriculoDto;
import com.isabela.TCC.domain.curriculo.model.Curriculo;
import com.isabela.TCC.domain.curriculo.repository.CurriculoRepository;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.profissional.repository.ProfissionalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;
    @Autowired
    private ProfissionalRepository profissionalRepository;
    @Transactional
    public VisualizarCurriculoDto cadastrarCurriculo(CadastrarCurriculoDto dto){
        Curriculo curriculo = new Curriculo();
        Profissional profissional = profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new EntityNotFoundException("Profissional com o ID: " + dto.getProfissionalId() + " não encontrado."));
        curriculo.setEscolaridade(dto.getEscolaridade());
        curriculo.setExperienciasProfissionais(dto.getExperienciaProfissionais());
        curriculo.setHabilidadesPessoais(dto.getHabilidadesPessoais());
        curriculo.setHabilidadesTecnicas(dto.getHabilidadesTecnicas());
        curriculo.setIdiomas(dto.getIdiomas());
        curriculo.setProfissional(profissional);

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
    public Page<VisualizarCurriculoDto> listarCurriculos(int pagina, int qtde){
        Pageable paginacao = PageRequest.of(pagina, qtde);
        Page<Curriculo> curriculo = curriculoRepository.findAll(paginacao);
        return curriculo.map(VisualizarCurriculoDto::copiarDaEntidadeProDto);
    }

    @Transactional
    public VisualizarCurriculoDto atualizarCurriculo(Long id, AtualizarCurriculoDto dto){
        Curriculo curriculoAtualizado = curriculoRepository.getReferenceById(id);
        try {
            curriculoAtualizado.setEscolaridade(dto.getEscolaridade());
            curriculoAtualizado.setExperienciasProfissionais(dto.getExperienciasProfissionais());
            curriculoAtualizado.setHabilidadesPessoais(dto.getHabilidadesPessoais());
            curriculoAtualizado.setHabilidadesTecnicas(dto.getHabilidadesTecnicas());
            curriculoAtualizado.setIdiomas(dto.getIdiomas());

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
}
