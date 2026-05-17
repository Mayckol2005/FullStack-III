package com.colegio.auth_service.controller;

import com.colegio.auth_service.client.UsuarioFeignClient;
import com.colegio.auth_service.dto.AuthUserDto;
import com.colegio.auth_service.dto.TokenDto;
import com.colegio.auth_service.dto.UsuarioDto;
import com.colegio.auth_service.security.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Seguridad y Accesos", description = "Endpoints para el inicio de sesión y generación de credenciales del colegio")
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Iniciar sesión", description = "Valida el correo y contraseña del usuario para otorgarle un Token de acceso al sistema")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto) {

        if(authUserDto.getEmail() == null || authUserDto.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            // 1. Buscamos al usuario real a través de Feign comunicándonos con usuario-service
            UsuarioDto usuarioReal = usuarioFeignClient.buscarPorEmail(authUserDto.getEmail());

            if (usuarioReal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // 2. Comparamos la contraseña en texto plano con el Hash de la base de datos
            if (passwordEncoder.matches(authUserDto.getPassword(), usuarioReal.getPassword())) {

                // 3. Si coinciden, generamos el token con su ROL REAL
                String token = jwtProvider.createToken(usuarioReal.getEmail(), usuarioReal.getRol());

                // 4. Enviamos el token Y EL ROL en la respuesta
                return ResponseEntity.ok(new TokenDto(token, usuarioReal.getRol()));

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}