package com.colegio.evaluacion_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;

    @NotBlank(message = "La asignatura es obligatoria")
    private String asignatura;

    @Min(value = 1, message = "La nota mínima es 1.0")
    @Max(value = 7, message = "La nota máxima es 7.0")
    @NotNull(message = "La nota es obligatoria")
    private Double nota;

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