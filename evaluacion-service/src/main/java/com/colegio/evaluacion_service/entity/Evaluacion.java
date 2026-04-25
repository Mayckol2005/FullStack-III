package com.colegio.evaluacion_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "evaluaciones")
@Schema(description = "Entidad que representa una calificación obtenida por un estudiante")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador interno y único de la evaluación", example = "1")
    private Long id;

    @NotNull(message = "El ID del estudiante es obligatorio")
    @Schema(description = "Identificador del estudiante evaluado", example = "123")
    private Long estudianteId;

    @NotBlank(message = "La asignatura es obligatoria")
    @Schema(description = "Nombre de la materia o ramo", example = "Matemáticas")
    private String asignatura;

    @Min(value = 1, message = "La nota mínima es 1.0")
    @Max(value = 7, message = "La nota máxima es 7.0")
    @NotNull(message = "La nota es obligatoria")
    @Schema(description = "Calificación obtenida (escala 1.0 a 7.0)", example = "6.5")
    private Double nota;

    @Schema(description = "Fecha en que se registró la nota", example = "2026-04-25")
    private LocalDate fecha = LocalDate.now();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}