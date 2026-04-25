package com.colegio.academico_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "asignaturas")
@Schema(description = "Entidad que representa una materia o ramo dictado en un curso")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado de la asignatura", example = "1")
    private Long id;

    @NotBlank(message = "El nombre de la asignatura es obligatorio")
    @Schema(description = "Nombre de la materia", example = "Matemáticas")
    private String nombre;

    @NotNull(message = "El ID del curso es obligatorio")
    @Schema(description = "ID del curso al que pertenece esta asignatura", example = "1")
    private Long cursoId;

    @NotNull(message = "El ID del docente es obligatorio")
    @Schema(description = "ID del usuario (Docente) que imparte la materia", example = "5")
    private Long docenteId;

    public Asignatura() {}

    public Asignatura(String nombre, Long cursoId, Long docenteId) {
        this.nombre = nombre;
        this.cursoId = cursoId;
        this.docenteId = docenteId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
    public Long getDocenteId() { return docenteId; }
    public void setDocenteId(Long docenteId) { this.docenteId = docenteId; }
}