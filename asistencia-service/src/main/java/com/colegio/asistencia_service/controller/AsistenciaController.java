package com.colegio.asistencia_service.controller;

import com.colegio.asistencia_service.entity.Asistencia;
import com.colegio.asistencia_service.service.AsistenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints para el control de asistencia diaria.
 * Permite a los docentes visualizar los registros y enviar la lista de clases.
 */
@RestController
@RequestMapping("/api/asistencia")
@Tag(name = "Control de Asistencia", description = "Endpoints para registrar la presencia diaria de los alumnos")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    /**
     * Endpoint para obtener el historial completo de asistencia del establecimiento.
     * @return ResponseEntity con la lista de registros y código HTTP 200 (OK).
     */
    @Operation(summary = "Listar registro general", description = "Obtiene el historial completo de asistencia del colegio")
    @GetMapping
    public ResponseEntity<List<Asistencia>> listarTodas() {
        return ResponseEntity.ok(asistenciaService.listarTodas());
    }

    /**
     * Endpoint para registrar el estado de asistencia de un alumno (presente o ausente).
     * @param asistencia Objeto JSON validado con los datos de asistencia a registrar.
     * @return ResponseEntity con el registro creado y código HTTP 201 (CREATED).
     */
    @Operation(summary = "Pasar la lista", description = "Registra el estado de asistencia (presente/ausente) de un estudiante en una fecha")
    @PostMapping
    public ResponseEntity<Asistencia> guardar(@Valid @RequestBody Asistencia asistencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(asistenciaService.guardar(asistencia));
    }
}