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

/**
 * Controlador REST responsable de la autenticación centralizada de usuarios.
 * Provee el endpoint para el inicio de sesión del ecosistema y delega la verificación 
 * de contraseñas.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Seguridad y Accesos", description = "Endpoints para el inicio de sesión")
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Endpoint central para iniciar sesión en el portal.
     * Consulta al usuario-service mediante Feign, verifica la contraseña hash con BCrypt 
     * y genera un token JWT si el proceso es exitoso.
     * @param authUserDto Objeto DTO que encapsula el email y la contraseña en texto plano.
     * @return ResponseEntity con el TokenDto (token, rol, id y nombre) y código 200 (OK),
     * o código HTTP 401 (UNAUTHORIZED) si las credenciales fallan.
     */
    @Operation(summary = "Iniciar sesión")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto) {
        try {
            UsuarioDto usuarioReal = usuarioFeignClient.buscarPorEmail(authUserDto.getEmail());

            if (usuarioReal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            boolean esValida = passwordEncoder.matches(authUserDto.getPassword(), usuarioReal.getPassword());

            if (esValida) {
                String token = jwtProvider.createToken(usuarioReal.getEmail(), usuarioReal.getRol());
                
                return ResponseEntity.ok(new TokenDto(
                    token, 
                    usuarioReal.getRol(), 
                    usuarioReal.getId(), 
                    usuarioReal.getNombre()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}