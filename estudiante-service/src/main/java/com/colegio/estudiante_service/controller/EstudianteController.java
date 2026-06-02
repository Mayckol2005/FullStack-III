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

/**
 * Controlador REST que expone los endpoints para la gestión de estudiantes.
 * Maneja las peticiones HTTP y delega la lógica de negocio al EstudianteService.
 */
@RestController
@RequestMapping("/api/estudiantes")
@Tag(name = "Gestión de Estudiantes", description = "Endpoints para la matrícula y consulta de alumnos")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    /**
     * Endpoint para matricular a un nuevo estudiante en el sistema.
     * @param estudiante Objeto JSON con los datos del estudiante validados.
     * @return ResponseEntity con el estudiante creado y código HTTP 201 (CREATED).
     */
    @Operation(summary = "Matricular estudiante", description = "Registra un nuevo alumno en la base de datos del colegio")
    @PostMapping
    public ResponseEntity<Estudiante> matricularEstudiante(@Valid @RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteService.registrarEstudiante(estudiante);
        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obtener la lista de todos los estudiantes del colegio.
     * @return ResponseEntity con la lista de estudiantes y código HTTP 200 (OK).
     */
    @Operation(summary = "Listar todos", description = "Devuelve una lista completa de los estudiantes matriculados")
    @GetMapping
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.obtenerTodos());
    }

    /**
     * Endpoint para buscar un estudiante específico por su ID.
     * @param id Identificador único del estudiante en la ruta.
     * @return ResponseEntity con el estudiante encontrado (200 OK) o error 404 (NOT FOUND).
     */
    @Operation(summary = "Obtener estudiante por ID", description = "Busca los datos de un alumno mediante su identificador único")
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.obtenerPorId(id);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para listar todos los estudiantes que pertenecen a un curso específico.
     * @param cursoId Identificador del curso en la ruta.
     * @return ResponseEntity con la lista de estudiantes filtrada y código HTTP 200 (OK).
     */
    @Operation(summary = "Listar estudiantes por curso", description = "Devuelve la nómina de alumnos que pertenecen a un curso específico")
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Estudiante>> listarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(estudianteService.obtenerPorCurso(cursoId));
    }

    /**
     * Endpoint para actualizar la información de un estudiante existente.
     * @param id Identificador del estudiante a modificar.
     * @param detalles Objeto JSON con los nuevos datos del estudiante validados.
     * @return ResponseEntity con el estudiante modificado (200 OK) o error 404 (NOT FOUND).
     */
    @Operation(summary = "Actualizar estudiante", description = "Modifica los datos de un alumno existente")
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long id, @Valid @RequestBody Estudiante detalles) {
        try {
            Estudiante actualizado = estudianteService.actualizarEstudiante(id, detalles);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para eliminar a un estudiante del sistema.
     * @param id Identificador del estudiante a eliminar.
     * @return ResponseEntity vacío con código HTTP 204 (NO CONTENT).
     */
    @Operation(summary = "Eliminar estudiante", description = "Elimina a un alumno del sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}