package com.colegio.anotacion_service.controller;

import com.colegio.anotacion_service.entity.Anotacion;
import com.colegio.anotacion_service.service.AnotacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anotaciones")
@Tag(name = "Libro de Clases (Conducta)", description = "Endpoints para la gestión de anotaciones positivas y negativas")
public class AnotacionController {

    @Autowired
    private AnotacionService anotacionService;

    @Operation(summary = "Listar historial completo", description = "Obtiene el registro total de anotaciones conductuales del colegio")
    @GetMapping
    public ResponseEntity<List<Anotacion>> listarTodas() {
        return ResponseEntity.ok(anotacionService.listarTodas());
    }

    @Operation(summary = "Ver hoja de vida", description = "Obtiene todas las anotaciones asociadas a un estudiante en específico")
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<Anotacion>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(anotacionService.listarPorEstudiante(id));
    }

    @Operation(summary = "Crear anotación", description = "Registra una nueva observación en la hoja de vida del estudiante")
    @PostMapping
    public ResponseEntity<Anotacion> guardar(@Valid @RequestBody Anotacion anotacion) {
        Anotacion nuevaAnotacion = anotacionService.guardar(anotacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAnotacion);
    }
}