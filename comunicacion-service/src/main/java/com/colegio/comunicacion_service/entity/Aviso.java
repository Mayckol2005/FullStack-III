package com.colegio.comunicacion_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "avisos")
@Schema(description = "Entidad que representa un aviso o comunicado institucional")
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado del aviso", example = "1")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El título del aviso es obligatorio")
    @Size(max = 100, message = "El título no puede exceder los 100 caracteres")
    @Schema(description = "Título principal del comunicado", example = "Suspensión de clases")
    private String titulo;

    @Column(nullable = false, length = 1000)
    @NotBlank(message = "El contenido del aviso es obligatorio")
    @Schema(description = "Cuerpo detallado del mensaje", example = "Mañana no hay clases por lluvia extrema.")
    private String contenido;

    @Column(nullable = false)
    @NotBlank(message = "El remitente es obligatorio (ej. Dirección, Profesor Jefe)")
    @Schema(description = "Quién emite el comunicado", example = "Dirección Académica")
    private String remitente;

    @Schema(description = "Fecha y hora en la que se generó el comunicado automáticamente")
    private LocalDateTime fechaPublicacion;

    // Constructor
    public Aviso() {
        this.fechaPublicacion = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getRemitente() { return remitente; }
    public void setRemitente(String remitente) { this.remitente = remitente; }

    public LocalDateTime getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDateTime fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
}