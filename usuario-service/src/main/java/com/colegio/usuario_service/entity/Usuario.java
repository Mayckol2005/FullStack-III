package com.colegio.usuario_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
@Schema(description = "Entidad que representa un usuario del sistema escolar (Alumno, Profesor, Apoderado o Admin)")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado del usuario", example = "1")
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El RUT es obligatorio")
    @Schema(description = "RUT del usuario con guión y dígito verificador", example = "12345678-9")
    private String rut;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String nombre;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido")
    @Schema(description = "Correo electrónico de contacto", example = "juan.perez@colegio.cl")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Schema(description = "Contraseña de acceso", example = "password123")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "El rol es obligatorio")
    @Schema(description = "Rol del usuario en el sistema", example = "ADMINISTRADOR")
    private String rol;

    public Usuario() {
    }

    public Usuario(String rut, String nombre, String email, String password, String rol) {
        this.rut = rut;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}