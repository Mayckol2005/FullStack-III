package com.colegio.academico_service.controller;

import com.colegio.academico_service.entity.Curso;
import com.colegio.academico_service.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academico/cursos")
@Tag(name = "Gestión de Cursos", description = "Endpoints para el mantenimiento de los cursos del liceo")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Operation(summary = "Listar todos los cursos", description = "Obtiene una lista completa de todos los cursos registrados")
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @Operation(summary = "Obtener curso por ID", description = "Busca y retorna un curso específico según su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id);
        if(curso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curso);
    }

    @Operation(summary = "Crear un nuevo curso", description = "Registra un nuevo curso en la base de datos")
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }
}