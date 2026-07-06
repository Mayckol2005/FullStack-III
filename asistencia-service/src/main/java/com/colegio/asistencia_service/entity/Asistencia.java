package com.colegio.asistencia_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(
        name = "asistencias",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_asistencia_fecha_estudiante",
                        columnNames = {"fecha", "estudiante_id"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_asistencia_curso_fecha",
                        columnList = "curso_id, fecha"
                )
        }
)
@Schema(
        description = "Entidad que registra la presencia o ausencia diaria de un estudiante"
)
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único del registro de asistencia",
            example = "1"
    )
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha", nullable = false)
    @Schema(
            description = "Fecha correspondiente a la toma de asistencia",
            example = "2026-07-08"
    )
    private LocalDate fecha;

    @NotNull(message = "El ID del curso es obligatorio")
    @Column(name = "curso_id", nullable = false)
    @Schema(
            description = "Identificador del curso al que corresponde la asistencia",
            example = "1"
    )
    private Long cursoId;

    @NotNull(message = "El ID del estudiante es obligatorio")
    @Column(name = "estudiante_id", nullable = false)
    @Schema(
            description = "Identificador del estudiante",
            example = "123"
    )
    private Long estudianteId;

    @Column(name = "presente", nullable = false)
    @Schema(
            description = "Indica si el estudiante asistió a clases (true = Presente, false = Ausente)",
            example = "true"
    )
    private boolean presente;

    @Column(name = "observacion", length = 500)
    @Schema(
            description = "Justificación u observación adicional asociada al registro de asistencia",
            example = "Llegó 15 minutos tarde por problemas de locomoción"
    )
    private String observacion;

    public Asistencia() {
    }

    public Asistencia(
            LocalDate fecha,
            Long cursoId,
            Long estudianteId,
            boolean presente,
            String observacion
    ) {
        this.fecha = fecha;
        this.cursoId = cursoId;
        this.estudianteId = estudianteId;
        this.presente = presente;
        this.observacion = observacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}