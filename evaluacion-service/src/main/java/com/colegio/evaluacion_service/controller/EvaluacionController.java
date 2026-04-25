package com.colegio.evaluacion_service.controller;

import com.colegio.evaluacion_service.entity.Evaluacion;
import com.colegio.evaluacion_service.service.EvaluacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public ResponseEntity<List<Evaluacion>> listarTodas() {
        return ResponseEntity.ok(evaluacionService.listarTodas());
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<Evaluacion>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacionService.listarPorEstudiante(id));
    }

    @PostMapping
    public ResponseEntity<Evaluacion> guardar(@Valid @RequestBody Evaluacion evaluacion) {
        Evaluacion nuevaEvaluacion = evaluacionService.guardar(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
    }

    // ENDPOINT PARA OBTENER EL PROMEDIO DE UNA ASIGNATURA
    @GetMapping("/estudiante/{id}/promedio")
    public ResponseEntity<Double> obtenerPromedio(
            @PathVariable Long id,
            @RequestParam String asignatura) {
        Double promedio = evaluacionService.calcularPromedioAsignatura(id, asignatura);
        return ResponseEntity.ok(promedio);
    }
}