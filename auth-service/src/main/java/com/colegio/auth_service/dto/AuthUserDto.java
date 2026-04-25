package com.colegio.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos requeridos para iniciar sesión en el portal del colegio")
public class AuthUserDto {

    @Schema(description = "Correo institucional del funcionario o apoderado", example = "profesor@liceo.cl")
    private String email;

    @Schema(description = "Contraseña de acceso al portal", example = "secreta123")
    private String password;

    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}