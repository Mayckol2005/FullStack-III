package com.colegio.asistencia_service.controller;

import com.colegio.asistencia_service.entity.Asistencia;
import com.colegio.asistencia_service.service.AsistenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST que expone los endpoints para el control de asistencia diaria.
 * Permite a los docentes visualizar los registros, consultar la asistencia
 * de un curso y sincronizar la lista de clases.
 */
@RestController
@RequestMapping("/api/asistencia")
@Tag(
        name = "Control de Asistencia",
        description = "Endpoints para registrar y consultar la presencia diaria de los estudiantes"
)
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    /**
     * Endpoint para obtener el historial completo de asistencia del establecimiento.
     *
     * @return ResponseEntity con la lista de registros y código HTTP 200 (OK).
     */
    @Operation(
            summary = "Listar registro general",
            description = "Obtiene el historial completo de asistencia del colegio"
    )
    @GetMapping
    public ResponseEntity<List<Asistencia>> listarTodas() {
        return ResponseEntity.ok(asistenciaService.listarTodas());
    }

    /**
     * Endpoint para obtener los registros de asistencia de un curso
     * correspondiente a una fecha específica.
     *
     * @param cursoId Identificador único del curso.
     * @param fecha Fecha correspondiente a la toma de asistencia.
     * @return ResponseEntity con los registros encontrados y código HTTP 200 (OK).
     */
    @Operation(
            summary = "Consultar asistencia por curso y fecha",
            description = "Obtiene los registros de asistencia de un curso para una fecha específica"
    )
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Asistencia>> listarPorCursoYFecha(
            @PathVariable Long cursoId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha
    ) {
        return ResponseEntity.ok(
                asistenciaService.listarPorCursoYFecha(cursoId, fecha)
        );
    }

    @Operation(
            summary = "Consultar asistencia por estudiante",
            description = "Obtiene los registros de asistencia de un estudiante"
    )
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Asistencia>> listarPorEstudiante(
            @PathVariable Long estudianteId
    ) {
        return ResponseEntity.ok(
                asistenciaService.listarPorEstudiante(estudianteId)
        );
    }

    /**
     * Endpoint para registrar o actualizar el estado de asistencia de un estudiante.
     *
     * @param asistencia Objeto JSON validado con los datos de asistencia a registrar.
     * @return ResponseEntity con el registro creado o actualizado.
     */
    @Operation(
            summary = "Registrar asistencia individual",
            description = "Registra o actualiza el estado de asistencia de un estudiante en una fecha"
    )
    @PostMapping
    public ResponseEntity<Asistencia> guardar(
            @Valid @RequestBody Asistencia asistencia
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(asistenciaService.guardar(asistencia));
    }

    /**
     * Endpoint para sincronizar una lista completa de asistencia.
     * Los registros existentes son actualizados y los nuevos son creados.
     *
     * @param asistencias Lista validada de registros de asistencia a sincronizar.
     * @return ResponseEntity con la lista de registros creados o actualizados
     *         y código HTTP 200 (OK).
     */
    @Operation(
            summary = "Sincronizar lista de asistencia",
            description = "Registra o actualiza en una sola operación la asistencia de los estudiantes de un curso"
    )
    @PutMapping("/lista")
    public ResponseEntity<List<Asistencia>> guardarLista(
            @Valid @RequestBody List<Asistencia> asistencias
    ) {
        return ResponseEntity.ok(
                asistenciaService.guardarLista(asistencias)
        );
    }
}
