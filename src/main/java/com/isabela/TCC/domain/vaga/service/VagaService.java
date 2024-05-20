package com.isabela.TCC.domain.vaga.service;

import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.empresa.repository.EmpresaRepository;
import com.isabela.TCC.domain.vaga.dto.AtualizarVagaDto;
import com.isabela.TCC.domain.vaga.dto.CadastrarVagaDto;
import com.isabela.TCC.domain.vaga.dto.VisualizarVagaComFiltroDto;
import com.isabela.TCC.domain.vaga.dto.VisualizarVagaDto;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.domain.vaga.repository.VagaRepository;
import com.isabela.TCC.enums.Situacao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        vaga.setEndereco(dto.);
        vaga.setSituacao(Situacao.NAO_ATIVO);
        vaga.setEmpresa(empresa);

        vagaRepository.save(vaga);
        return VisualizarVagaDto.copiarDaEntidadeProDto(vaga);
    }

    @Transactional
    public VisualizarVagaDto findVagaById(Long id){
       Vaga vagas = vagaRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Vaga com o ID: " + id + " não encontrada."));
       return VisualizarVagaDto.copiarDaEntidadeProDto(vagas);
    }

    @Transactional
    public Page<VisualizarVagaDto> listarVagas(int pagina, int qtde){
        Pageable paginacao = PageRequest.of(pagina, qtde);
        Page<Vaga> vagas = vagaRepository.findAll(paginacao);
        return vagas.map(VisualizarVagaDto::copiarDaEntidadeProDto);
    }

    @Transactional
    public VisualizarVagaDto atualizarVaga(Long id, AtualizarVagaDto dto){
        Vaga vagaAtualizada = vagaRepository.getReferenceById(id);
        try {
            vagaAtualizada.setTitulo(dto.getTitulo());
            vagaAtualizada.setDecricao(dto.getDescricao());
            vagaAtualizada.setCargo(dto.getCargo());
            vagaAtualizada.setUpdateAt(LocalDateTime.now());

            vagaRepository.save(vagaAtualizada);
            return VisualizarVagaDto.copiarDaEntidadeProDto(vagaAtualizada);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Vaga com o ID: " + id + " não encontrada.");
        }


    }
    @Transactional
    public void deletarVaga(Long id){
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vaga com o ID: " + id + " não encontrada."));
        vagaRepository.delete(vaga);
    }

    public Page<VisualizarVagaComFiltroDto> exibirVagasComFiltro(String titulo, String cargo)
}
