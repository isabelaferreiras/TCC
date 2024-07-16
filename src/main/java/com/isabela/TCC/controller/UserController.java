package com.isabela.TCC.controller;

import com.isabela.TCC.domain.usuario.dto.CreateUserDto;
import com.isabela.TCC.domain.usuario.dto.LoginUserDto;
import com.isabela.TCC.domain.usuario.dto.RecoveryJwtTokenDto;
import com.isabela.TCC.domain.usuario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/profissional")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return new ResponseEntity<>("Profissional autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/empresa")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return new ResponseEntity<>("Empresa autenticada com sucesso", HttpStatus.OK);
    }
}
