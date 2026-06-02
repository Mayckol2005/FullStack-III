package com.colegio.academico_service.controller;

import com.colegio.academico_service.entity.Asignatura;
import com.colegio.academico_service.service.AsignaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar las asignaturas del plan de estudio.
 * Permite consultar el catálogo de materias y asociarlas a cursos y docentes.
 */
@RestController
@RequestMapping("/api/academico/asignaturas")
@Tag(name = "Plan de Estudio", description = "Endpoints para gestionar las asignaturas impartidas en el colegio")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    /**
     * Endpoint para obtener todas las asignaturas del establecimiento.
     * @return ResponseEntity con la lista de asignaturas y código HTTP 200 (OK).
     */
    @Operation(summary = "Listar todas las materias", description = "Obtiene el catálogo completo de asignaturas del colegio")
    @GetMapping
    public ResponseEntity<List<Asignatura>> listarAsignaturas() {
        return ResponseEntity.ok(asignaturaService.listarTodas());
    }

    /**
     * Endpoint para obtener las asignaturas correspondientes a un curso.
     * @param cursoId Identificador del curso en la ruta.
     * @return ResponseEntity con la lista de asignaturas filtradas y código HTTP 200 (OK).
     */
    @Operation(summary = "Materias por curso", description = "Filtra y devuelve las asignaturas asignadas a un curso en particular")
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Asignatura>> listarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(asignaturaService.listarPorCurso(cursoId));
    }

    /**
     * Endpoint para crear una nueva asignatura.
     * @param asignatura Objeto JSON validado con los datos de la materia.
     * @return ResponseEntity con la asignatura creada y código HTTP 201 (CREATED).
     */
    @Operation(summary = "Crear asignatura", description = "Registra una nueva materia y la asocia a un curso y a un profesor")
    @PostMapping
    public ResponseEntity<Asignatura> crearAsignatura(@Valid @RequestBody Asignatura asignatura) {
        return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaService.guardar(asignatura));
    }
}