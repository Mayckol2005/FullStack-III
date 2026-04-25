package com.colegio.estudiante_service.controller;

import com.colegio.estudiante_service.entity.Estudiante;
import com.colegio.estudiante_service.service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
@Tag(name = "Gestión de Estudiantes", description = "Endpoints para la matrícula y consulta de alumnos")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Operation(summary = "Matricular estudiante", description = "Registra un nuevo alumno en la base de datos del colegio")
    @PostMapping
    public ResponseEntity<Estudiante> matricularEstudiante(@Valid @RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteService.registrarEstudiante(estudiante);
        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos", description = "Devuelve una lista completa de los estudiantes matriculados")
    @GetMapping
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.obtenerTodos());
    }

    @Operation(summary = "Obtener estudiante por ID", description = "Busca los datos de un alumno mediante su identificador único")
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.obtenerPorId(id);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar estudiantes por curso", description = "Devuelve la nómina de alumnos que pertenecen a un curso específico")
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Estudiante>> listarPorCurso(@PathVariable Long cursoId) {
        // CORREGIDO: Se llama a obtenerPorCurso() tal cual lo tienes en tu Service
        return ResponseEntity.ok(estudianteService.obtenerPorCurso(cursoId));
    }
}