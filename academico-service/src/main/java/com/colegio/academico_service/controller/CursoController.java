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
 * Expone los endpoints para consultar y registrar cursos con validación estricta de duplicados.
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
     * Valida que no exista otro curso con el mismo grado, letra y nivel educativo.
     * @param curso Objeto JSON con los datos del curso validados.
     * @return ResponseEntity con el curso creado (201) o un mensaje de error si está duplicado (409).
     */
    @Operation(summary = "Crear un nuevo curso", description = "Registra un nuevo curso verificando que no sea un duplicado")
    @PostMapping
    public ResponseEntity<?> crearCurso(@Valid @RequestBody Curso curso) {
        // 🛡️ Validación en el Backend: Evita duplicados cruzando los datos entrantes con la lista existente
        boolean yaExiste = cursoService.listarTodos().stream().anyMatch(c -> 
            c.getGrado().equalsIgnoreCase(curso.getGrado()) &&
            c.getLetra().equalsIgnoreCase(curso.getLetra()) &&
            c.getNivel().equalsIgnoreCase(curso.getNivel())
        );

        if (yaExiste) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("El curso " + curso.getGrado() + "° " + curso.getLetra() + " de nivel " + curso.getNivel() + " ya se encuentra registrado.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }
}