package com.colegio.auth_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Componente encargado de la generación, configuración y firma de tokens JWT (JSON Web Tokens).
 * Utiliza algoritmos criptográficos HMAC SHA para asegurar la integridad de los datos de acceso.
 */
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    /**
     * Genera un token JWT compacto y firmado criptográficamente para un usuario autenticado.
     * Añade los claims correspondientes al rol para la posterior autorización en el API Gateway.
     * @param email Correo electrónico institucional del usuario (almacenado como Subject).
     * @param rol Rol asignado en el sistema (ej. DOCENTE, ADMINISTRADOR) para control de accesos.
     * @return Cadena de caracteres que representa el token JWT codificado.
     */
    public String createToken(String email, String rol) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", rol); // Guardamos el rol dentro del token

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}