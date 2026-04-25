package com.colegio.estudiante_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "estudiantes")
@Schema(description = "Entidad que representa a un alumno matriculado en el liceo")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador interno del estudiante", example = "1")
    private Long id;

    @NotBlank(message = "El RUT es obligatorio")
    @Column(unique = true, nullable = false)
    @Schema(description = "RUT chileno del estudiante (sin puntos y con guion)", example = "21456789-K")
    private String rut;

    @NotBlank(message = "Los nombres son obligatorios")
    @Schema(description = "Nombres del alumno", example = "Juan Ignacio")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Schema(description = "Apellidos del alumno", example = "Pérez Silva")
    private String apellidos;

    @Email(message = "Debe ser un formato de correo válido")
    @NotBlank(message = "El email es obligatorio")
    @Column(unique = true, nullable = false)
    @Schema(description = "Correo institucional asignado al alumno", example = "juan.perez@liceo.cl")
    private String email;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Schema(description = "Fecha de nacimiento", example = "2010-05-15")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El ID del curso es obligatorio")
    @Schema(description = "Identificador del curso al que pertenece actualmente", example = "2")
    private Long cursoId;

    @Schema(description = "Estado actual de matrícula", example = "MATRICULADO")
    private String estado = "MATRICULADO";

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}