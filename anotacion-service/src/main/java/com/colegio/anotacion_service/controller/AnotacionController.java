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

/**
 * Controlador REST para el registro de la conducta escolar.
 * Expone endpoints para que los docentes ingresen y consulten anotaciones de los alumnos.
 */
@RestController
@RequestMapping("/api/anotaciones")
@Tag(name = "Libro de Clases (Conducta)", description = "Endpoints para la gestión de anotaciones positivas y negativas")
public class AnotacionController {

    @Autowired
    private AnotacionService anotacionService;

    /**
     * Endpoint para consultar todas las anotaciones a nivel institucional.
     * @return ResponseEntity con la lista de todas las anotaciones y código HTTP 200 (OK).
     */
    @Operation(summary = "Listar historial completo", description = "Obtiene el registro total de anotaciones conductuales del colegio")
    @GetMapping
    public ResponseEntity<List<Anotacion>> listarTodas() {
        return ResponseEntity.ok(anotacionService.listarTodas());
    }

    /**
     * Endpoint para consultar las anotaciones de un estudiante en particular.
     * @param id Identificador del estudiante en la ruta de la petición.
     * @return ResponseEntity con la hoja de vida del estudiante y código HTTP 200 (OK).
     */
    @Operation(summary = "Ver hoja de vida", description = "Obtiene todas las anotaciones asociadas a un estudiante en específico")
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<Anotacion>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(anotacionService.listarPorEstudiante(id));
    }

    /**
     * Endpoint para registrar una nueva observación sobre un estudiante.
     * @param anotacion Objeto JSON validado con los datos de la anotación.
     * @return ResponseEntity con la anotación creada y código HTTP 201 (CREATED).
     */
    @Operation(summary = "Crear anotación", description = "Registra una nueva observación en la hoja de vida del estudiante")
    @PostMapping
    public ResponseEntity<Anotacion> guardar(@Valid @RequestBody Anotacion anotacion) {
        Anotacion nuevaAnotacion = anotacionService.guardar(anotacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAnotacion);
    }
}