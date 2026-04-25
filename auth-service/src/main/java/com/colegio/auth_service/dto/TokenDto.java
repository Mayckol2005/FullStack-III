package com.colegio.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta exitosa que contiene la llave de acceso del usuario")
public class TokenDto {

    @Schema(description = "Token JWT (Bearer) que debe usarse para acceder a los demás microservicios", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}