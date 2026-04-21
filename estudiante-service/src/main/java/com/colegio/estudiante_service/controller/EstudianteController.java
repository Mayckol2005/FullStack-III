package com.colegio.estudiante_service.controller;

import com.colegio.estudiante_service.entity.Estudiante;
import com.colegio.estudiante_service.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // POST: /api/estudiantes (Matricular nuevo estudiante)
    @PostMapping
    public ResponseEntity<Estudiante> matricularEstudiante(@Valid @RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteService.registrarEstudiante(estudiante);
        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }

    // GET: /api/estudiantes (Listar todos)
    @GetMapping
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.obtenerTodos());
    }

    // GET: /api/estudiantes/{id} (Buscar uno específico)
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.obtenerPorId(id);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET: /api/estudiantes/curso/{cursoId} (Para cuando el profe pase asistencia)
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Estudiante>> listarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(estudianteService.obtenerPorCurso(cursoId));
    }
}