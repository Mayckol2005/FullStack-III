package com.colegio.anotacion_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "anotaciones")
@Schema(description = "Entidad que representa una observación en la hoja de vida (libro de clases) del estudiante")
public class Anotacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la anotación", example = "1")
    private Long id;

    @NotNull(message = "El ID del estudiante es obligatorio")
    @Schema(description = "Identificador del alumno que recibe la anotación", example = "123")
    private Long estudianteId;

    @NotNull(message = "El ID del docente que anota es obligatorio")
    @Schema(description = "Identificador del profesor que emite la anotación", example = "45")
    private Long docenteId;

    @NotBlank(message = "El tipo es obligatorio (POSITIVA, NEGATIVA, OBSERVACION)")
    @Schema(description = "Clasificación de la anotación", example = "POSITIVA")
    private String tipo;

    @NotBlank(message = "La descripción de la anotación es obligatoria")
    @Column(length = 500)
    @Schema(description = "Detalle del comportamiento o situación registrada", example = "El alumno destaca por su constante participación y compañerismo en clases.")
    private String descripcion;

    @Schema(description = "Fecha en que ocurrió el evento", example = "2026-04-25")
    private LocalDate fecha = LocalDate.now();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
    public Long getDocenteId() { return docenteId; }
    public void setDocenteId(Long docenteId) { this.docenteId = docenteId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}