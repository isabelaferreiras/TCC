package com.isabela.TCC.domain.empresa.service;

import com.isabela.TCC.domain.empresa.dto.AtualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.profissional.dto.CadastrarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalVagaDto;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.usuario.model.User;
import com.isabela.TCC.domain.usuario.repository.UserRepository;
import com.isabela.TCC.domain.usuario.role.UserRole;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.domain.vaga.repository.VagaRepository;
import com.isabela.TCC.enums.Situacao;
import com.isabela.TCC.domain.empresa.dto.CadastrarEmpresaDTO;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.empresa.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private VagaRepository vagaRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public VisualizarEmpresaDto cadastrarEmpresa(CadastrarEmpresaDTO dto){
        Empresa empresa = new Empresa();

        if (this.userRepository.findByLogin(dto.getEmail()) != null) throw new IllegalArgumentException("Usuário já existente");

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User newUser = new User(dto.getEmail(), encryptedPassword, UserRole.PROFISSIONAL);
        this.userRepository.save(newUser);

        empresa.setEmail(dto.getEmail());
        empresa.setNomeEmpresa(dto.getNomeEmpresa());
        empresa.setEndereco(dto.getEndereco());
        empresa.setDescricao(dto.getDescricao());
        empresa.setRamo(dto.getRamo());
        empresa.setCnpj(dto.getCnpj());
        empresa.setSituacao(Situacao.NAO_ATIVO);
        empresa.setCreateAt(LocalDateTime.now());
        empresa.setUpdateAt(LocalDateTime.now());
        empresa.setUser(newUser);

        empresaRepository.save(empresa);

        return VisualizarEmpresaDto.copiarDaEntidadeProDto(empresa);
    }


    @Transactional
    public VisualizarEmpresaDto FindEmpresaById(Long id){
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa com o ID: " + id + " não encontrada."));
        return VisualizarEmpresaDto.copiarDaEntidadeProDto(empresa);
    }

    @Transactional
    public Page<VisualizarEmpresaDto> listarEmpresas(int pagina, int quantidade){

        Pageable paginacao = PageRequest.of(pagina, quantidade);

        Page<Empresa> empresas = empresaRepository.findAll(paginacao);
        return empresas.map(VisualizarEmpresaDto::copiarDaEntidadeProDto);
    }

    @Transactional
    public VisualizarEmpresaDto atualizarDadosEmpresa(Long id, AtualizarEmpresaDto dto){
        Empresa empresaAtualizada = empresaRepository.getReferenceById(id);
        try {
            empresaAtualizada.setNomeEmpresa(dto.getNomeEmpresa());
            empresaAtualizada.setDescricao(dto.getDescricao());
            empresaAtualizada.setRamo(dto.getRamo());
            empresaAtualizada.setEndereco(dto.getEndereco());
            empresaAtualizada.setUpdateAt(LocalDateTime.now());

            empresaRepository.save(empresaAtualizada);
            return VisualizarEmpresaDto.copiarDaEntidadeProDto(empresaAtualizada);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Empresa com o ID: " + id + " não encontrada.");
        }

    }

    @Transactional
    public void deletarEmpresa(Long id){
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa com o ID: " + id + "não encontrada."));
        empresaRepository.delete(empresa);

    }

    @Transactional
    public List<VisualizarProfissionalVagaDto> visualizarProfissionaisCadastrados(Long vagaId) {
        Vaga vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new EntityNotFoundException("Vaga não encontrada"));

        return vaga.getProfissionais().stream()
                .map(VisualizarProfissionalVagaDto::copiarDaEntidadeProDto)
                .collect(Collectors.toList());
    }

}
