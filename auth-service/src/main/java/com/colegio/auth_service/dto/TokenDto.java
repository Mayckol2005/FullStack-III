package com.colegio.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta exitosa que contiene la llave de acceso y el rol del usuario")
public class TokenDto {

    private String token;
    private String rol; 
    private Long id;

    public TokenDto(String token, String rol, Long id) {
        this.token = token;
        this.rol = rol;
        this.id = id;
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters y Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}