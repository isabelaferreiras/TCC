package com.isabela.TCC.controller;

import com.isabela.TCC.domain.usuario.LoginResponseDto;
import com.isabela.TCC.domain.usuario.dto.AuthenticationDto;
import com.isabela.TCC.domain.usuario.dto.RegisterDto;
import com.isabela.TCC.domain.usuario.model.User;
import com.isabela.TCC.domain.usuario.repository.UserRepository;
import com.isabela.TCC.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        var user = (User) auth.getPrincipal();

        // Verifica se a empresa existe antes de tentar pegar o ID
        Long empresaId = user.getEmpresa() != null ? user.getEmpresa().getId() : null;

        // Verifica se o profissional existe antes de tentar pegar o ID
        Long userId = user.getProfissional() != null ? user.getProfissional().getId() : null;

        var role = ((User) auth.getPrincipal()).getRole();

        return ResponseEntity.ok(new LoginResponseDto(token, empresaId, userId, role));
    }

    /*@PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data){
        if (this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }*/
}
