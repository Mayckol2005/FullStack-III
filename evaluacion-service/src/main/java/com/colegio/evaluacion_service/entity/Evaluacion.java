package com.colegio.evaluacion_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(
        name = "evaluaciones",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_evaluacion_estudiante_asignatura_numero",
                        columnNames = {
                                "estudiante_id",
                                "asignatura_id",
                                "numero_evaluacion"
                        }
                )
        },
        indexes = {
                @Index(
                        name = "idx_evaluacion_asignatura_numero",
                        columnList = "asignatura_id, numero_evaluacion"
                )
        }
)
@Schema(
        description = "Entidad que representa una calificación obtenida por un estudiante"
)
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador interno y único de la evaluación",
            example = "1"
    )
    private Long id;

    @NotNull(message = "El ID del estudiante es obligatorio")
    @Column(name = "estudiante_id", nullable = false)
    @Schema(
            description = "Identificador del estudiante evaluado",
            example = "123"
    )
    private Long estudianteId;

    @NotNull(message = "El ID de la asignatura es obligatorio")
    @Column(name = "asignatura_id", nullable = false)
    @Schema(
            description = "Identificador de la asignatura evaluada",
            example = "5"
    )
    private Long asignaturaId;

    @NotNull(message = "El número de evaluación es obligatorio")
    @Min(
            value = 1,
            message = "El número de evaluación mínimo es 1"
    )
    @Max(
            value = 3,
            message = "El número de evaluación máximo es 3"
    )
    @Column(name = "numero_evaluacion", nullable = false)
    @Schema(
            description = "Posición de la evaluación dentro del registro de notas: N1, N2 o N3",
            example = "1",
            allowableValues = {"1", "2", "3"}
    )
    private Integer numeroEvaluacion;

    @NotNull(message = "La nota es obligatoria")
    @DecimalMin(
            value = "1.0",
            message = "La nota mínima es 1.0"
    )
    @DecimalMax(
            value = "7.0",
            message = "La nota máxima es 7.0"
    )
    @Column(name = "nota", nullable = false)
    @Schema(
            description = "Calificación obtenida en escala de 1.0 a 7.0",
            example = "6.5"
    )
    private Double nota;

    @Column(name = "fecha", nullable = false)
    @Schema(
            description = "Fecha en que se registró la calificación",
            example = "2026-07-08"
    )
    private LocalDate fecha = LocalDate.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Long asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public Integer getNumeroEvaluacion() {
        return numeroEvaluacion;
    }

    public void setNumeroEvaluacion(Integer numeroEvaluacion) {
        this.numeroEvaluacion = numeroEvaluacion;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}