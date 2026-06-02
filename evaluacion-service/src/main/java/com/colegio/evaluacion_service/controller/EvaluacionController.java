package com.colegio.evaluacion_service.controller;

import com.colegio.evaluacion_service.entity.Evaluacion;
import com.colegio.evaluacion_service.service.EvaluacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints para la gestión de notas escolares.
 * Permite listar calificaciones, registrar nuevas notas y calcular promedios.
 */
@RestController
@RequestMapping("/api/evaluaciones")
@Tag(name = "Evaluaciones", description = "Endpoints para el registro de notas y cálculo de promedios")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    /**
     * Endpoint para consultar el registro histórico de todas las notas a nivel general.
     * @return ResponseEntity con la lista completa de evaluaciones y código HTTP 200 (OK).
     */
    @Operation(summary = "Listar todas las evaluaciones", description = "Devuelve el registro histórico de todas las notas del colegio")
    @GetMapping
    public ResponseEntity<List<Evaluacion>> listarTodas() {
        return ResponseEntity.ok(evaluacionService.listarTodas());
    }

    /**
     * Endpoint para consultar el boletín de calificaciones de un estudiante en particular.
     * @param id Identificador único del estudiante ingresado en la ruta.
     * @return ResponseEntity con las notas del estudiante y código HTTP 200 (OK).
     */
    @Operation(summary = "Listar evaluaciones por estudiante", description = "Obtiene todas las notas asociadas al ID de un estudiante específico")
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<Evaluacion>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacionService.listarPorEstudiante(id));
    }

    /**
     * Endpoint para que un docente ingrese una calificación al sistema.
     * @param evaluacion Objeto JSON validado con la nota y datos del alumno.
     * @return ResponseEntity con la calificación guardada y código HTTP 201 (CREATED).
     */
    @Operation(summary = "Registrar nueva evaluación", description = "Guarda una nueva calificación para un estudiante en una asignatura")
    @PostMapping
    public ResponseEntity<Evaluacion> guardar(@Valid @RequestBody Evaluacion evaluacion) {
        Evaluacion nuevaEvaluacion = evaluacionService.guardar(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
    }

    /**
     * Endpoint dinámico para calcular y consultar el promedio de un estudiante en un ramo específico.
     * @param id Identificador único del estudiante.
     * @param asignatura Nombre de la materia pasado como parámetro de consulta (?asignatura=...).
     * @return ResponseEntity con el valor numérico del promedio y código HTTP 200 (OK).
     */
    @Operation(summary = "Calcular promedio", description = "Calcula y retorna el promedio aritmético de un estudiante en una asignatura específica")
    @GetMapping("/estudiante/{id}/promedio")
    public ResponseEntity<Double> obtenerPromedio(
            @PathVariable Long id,
            @RequestParam String asignatura) {
        Double promedio = evaluacionService.calcularPromedioAsignatura(id, asignatura);
        return ResponseEntity.ok(promedio);
    }
}