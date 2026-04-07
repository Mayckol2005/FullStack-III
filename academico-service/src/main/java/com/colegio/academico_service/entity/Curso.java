package com.colegio.academico_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grado;
    private String letra;
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