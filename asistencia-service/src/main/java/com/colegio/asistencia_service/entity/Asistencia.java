package com.colegio.asistencia_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "asistencias")
@Schema(description = "Entidad que registra la presencia o ausencia diaria de un alumno")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del registro de asistencia", example = "1")
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    @Schema(description = "Fecha correspondiente a la toma de asistencia", example = "2026-04-25")
    private LocalDate fecha;

    @NotNull(message = "El ID del estudiante es obligatorio")
    @Schema(description = "Identificador del alumno", example = "123")
    private Long estudianteId;

    @Schema(description = "Indica si el alumno asistió a clases (true = Presente, false = Ausente)", example = "true")
    private boolean presente;

    @Schema(description = "Justificación o nota adicional", example = "Llegó 15 minutos tarde por problemas de locomoción")
    private String observacion;

    // Constructores
    public Asistencia() {}

    public Asistencia(LocalDate fecha, Long estudianteId, boolean presente, String observacion) {
        this.fecha = fecha;
        this.estudianteId = estudianteId;
        this.presente = presente;
        this.observacion = observacion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
    public boolean isPresente() { return presente; }
    public void setPresente(boolean presente) { this.presente = presente; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}