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

@RestController
@RequestMapping("/api/evaluaciones")
@Tag(name = "Evaluaciones", description = "Endpoints para el registro de notas y cálculo de promedios")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @Operation(summary = "Listar todas las evaluaciones", description = "Devuelve el registro histórico de todas las notas del colegio")
    @GetMapping
    public ResponseEntity<List<Evaluacion>> listarTodas() {
        return ResponseEntity.ok(evaluacionService.listarTodas());
    }

    @Operation(summary = "Listar evaluaciones por estudiante", description = "Obtiene todas las notas asociadas al ID de un estudiante específico")
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<Evaluacion>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacionService.listarPorEstudiante(id));
    }

    @Operation(summary = "Registrar nueva evaluación", description = "Guarda una nueva calificación para un estudiante en una asignatura")
    @PostMapping
    public ResponseEntity<Evaluacion> guardar(@Valid @RequestBody Evaluacion evaluacion) {
        Evaluacion nuevaEvaluacion = evaluacionService.guardar(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
    }

    @Operation(summary = "Calcular promedio", description = "Calcula y retorna el promedio aritmético de un estudiante en una asignatura específica")
    @GetMapping("/estudiante/{id}/promedio")
    public ResponseEntity<Double> obtenerPromedio(
            @PathVariable Long id,
            @RequestParam String asignatura) {
        Double promedio = evaluacionService.calcularPromedioAsignatura(id, asignatura);
        return ResponseEntity.ok(promedio);
    }
}