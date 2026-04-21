package com.colegio.academico_service.controller;

import com.colegio.academico_service.entity.Curso;
import com.colegio.academico_service.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/academico/cursos")
@Tag(name = "Gestión Académica de Cursos", description = "Endpoints para crear, listar y eliminar cursos del colegio")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Operation(summary = "Crear un nuevo curso", description = "Registra un nuevo curso en la malla académica validando sus campos.")
    @PostMapping("/crear")
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody Curso curso) {
        Curso nuevoCurso = cursoRepository.save(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    @Operation(summary = "Listar todos los cursos", description = "Devuelve el listado completo de cursos registrados en el sistema.")
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return ResponseEntity.ok(cursos);
    }

    @Operation(summary = "Obtener curso por ID", description = "Busca los detalles de un curso específico utilizando su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un curso", description = "Elimina un curso del registro académico mediante su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}