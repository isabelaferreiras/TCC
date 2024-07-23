package com.isabela.TCC.domain.profissional.service;

import com.isabela.TCC.domain.empresa.dto.VisualizarEmpresaDto;
import com.isabela.TCC.domain.empresa.model.Empresa;
import com.isabela.TCC.domain.profissional.dto.AtualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.CadastrarProfissionalDto;
import com.isabela.TCC.domain.profissional.dto.VisualizarProfissionalDto;
import com.isabela.TCC.domain.profissional.model.Profissional;
import com.isabela.TCC.domain.profissional.repository.ProfissionalRepository;
import com.isabela.TCC.domain.usuario.dto.AuthenticationDto;
import com.isabela.TCC.domain.usuario.model.User;
import com.isabela.TCC.domain.usuario.repository.UserRepository;
import com.isabela.TCC.domain.usuario.role.UserRole;
import com.isabela.TCC.domain.vaga.dto.VisualizarVagaProfissionalDto;
import com.isabela.TCC.domain.vaga.model.Vaga;
import com.isabela.TCC.domain.vaga.repository.VagaRepository;
import com.isabela.TCC.exceptions.LimiteDeInscricoesAtingido;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;
    @Autowired
    private VagaRepository vagaRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public VisualizarProfissionalDto cadastrarProfissional(CadastrarProfissionalDto dto){
        Profissional profissional = new Profissional();

        if (this.userRepository.findByLogin(dto.getEmail()) != null) throw new IllegalArgumentException("Usuário já existente");

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User newUser = new User(dto.getEmail(), encryptedPassword, UserRole.PROFISSIONAL);
        this.userRepository.save(newUser);

        profissional.setEmail(dto.getEmail());
        profissional.setNome(dto.getNome());
        profissional.setDataNascimento(LocalDate.parse(dto.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        profissional.setEndereco(dto.getEndereco());
        profissional.setCreateAt(LocalDateTime.now());
        profissional.setUpdateAt(LocalDateTime.now());
        profissional.setUser(newUser);

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

    @Transactional
    public void cadastrarProfissionalNaVaga(Long profissionalId, Long vagaId){
        Vaga vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new EntityNotFoundException("Vaga com o ID: " + vagaId + " não encontrada."));
        Profissional profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new EntityNotFoundException("Profissional com o ID: " + profissionalId + " não encontrado."));

        if (vaga.getLimite() != null && vaga.getLimite() && vaga.getLimiteProfissionais() != null) {
            int inscricoesAtuais = vaga.getProfissionais().size();
            if (inscricoesAtuais >= vaga.getLimiteProfissionais()) {
                throw new LimiteDeInscricoesAtingido("O limite de profissionais para esta vaga já foi atingido.");
            }
        }

        vaga.getProfissionais().add(profissional);
        profissional.getVagas().add(vaga);
        profissionalRepository.save(profissional);
        vagaRepository.save(vaga);
    }

    @Transactional
    public List<VisualizarVagaProfissionalDto> visualizarVagasCadastradas(Long profissionalId) {
        Profissional profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"));

        return profissional.getVagas().stream()
                .map(VisualizarVagaProfissionalDto::copiarDaEntidadeProDto)
                .collect(Collectors.toList());
    }

}
