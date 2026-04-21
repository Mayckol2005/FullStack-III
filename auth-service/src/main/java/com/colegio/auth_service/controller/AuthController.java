package com.colegio.auth_service.controller;

import com.colegio.auth_service.dto.AuthUserDto;
import com.colegio.auth_service.dto.TokenDto;
import com.colegio.auth_service.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto) {
        // TODO: Aquí en el futuro validaremos contra la Base de Datos o el Microservicio de Usuarios.
        // Por ahora, si manda un email y password, le fabricamos un token simulando que es DOCENTE.

        if(authUserDto.getEmail() == null || authUserDto.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Generamos el token mágico
        String token = jwtProvider.createToken(authUserDto.getEmail(), "ROLE_DOCENTE");

        return ResponseEntity.ok(new TokenDto(token));
    }
}