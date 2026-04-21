package com.colegio.academico_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cursos")
@Schema(description = "Entidad que representa un curso académico dentro del colegio")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado del curso", example = "1")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El grado es obligatorio (ej. 1ro, 2do)")
    @Schema(description = "Grado correspondiente al curso", example = "1ro")
    private String grado;

    @Column(nullable = false)
    @NotBlank(message = "La letra del curso es obligatoria (ej. A, B)")
    @Schema(description = "Letra o sección del curso", example = "A")
    private String letra;

    @Column(nullable = false)
    @NotBlank(message = "El nivel educativo es obligatorio (ej. Básica, Media)")
    @Schema(description = "Nivel educativo al que pertenece el curso", example = "Media")
    private String nivel;

    // Constructores
    public Curso() {}

    public Curso(String grado, String letra, String nivel) {
        this.grado = grado;
        this.letra = letra;
        this.nivel = nivel;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }

    public String getLetra() { return letra; }
    public void setLetra(String letra) { this.letra = letra; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
}