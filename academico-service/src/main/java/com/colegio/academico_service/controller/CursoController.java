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

/**
 * Controlador REST para la administración de los cursos académicos.
 * Expone los endpoints para consultar y registrar cursos en el sistema.
 */
@RestController
@RequestMapping("/api/academico/cursos")
@Tag(name = "Gestión de Cursos", description = "Endpoints para el mantenimiento de los cursos del liceo")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    /**
     * Endpoint para obtener la lista de todos los cursos.
     * @return ResponseEntity con la lista de cursos y código HTTP 200 (OK).
     */
    @Operation(summary = "Listar todos los cursos", description = "Obtiene una lista completa de todos los cursos registrados")
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    /**
     * Endpoint para consultar un curso por su ID.
     * @param id Identificador del curso en la ruta.
     * @return ResponseEntity con el curso (200 OK) o un error 404 (NOT FOUND) si no existe.
     */
    @Operation(summary = "Obtener curso por ID", description = "Busca y retorna un curso específico según su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id);
        if(curso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curso);
    }

    /**
     * Endpoint para registrar un nuevo curso académico.
     * @param curso Objeto JSON con los datos del curso validados.
     * @return ResponseEntity con el curso creado y código HTTP 201 (CREATED).
     */
    @Operation(summary = "Crear un nuevo curso", description = "Registra un nuevo curso en la base de datos")
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }
}