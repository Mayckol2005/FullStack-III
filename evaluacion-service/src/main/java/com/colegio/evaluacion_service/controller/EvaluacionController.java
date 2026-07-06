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
 * Controlador REST que expone los endpoints para la gestión de evaluaciones
 * y calificaciones de los estudiantes.
 * Permite consultar notas por estudiante o asignatura, registrar evaluaciones,
 * sincronizar las calificaciones N1, N2 y N3 y calcular promedios.
 */
@RestController
@RequestMapping("/api/evaluaciones")
@Tag(
        name = "Evaluaciones",
        description = "Endpoints para el registro, consulta y sincronización de calificaciones escolares"
)
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    /**
     * Endpoint para consultar el registro histórico completo
     * de evaluaciones del establecimiento.
     *
     * @return ResponseEntity con la lista completa de evaluaciones
     *         y código HTTP 200 (OK).
     */
    @Operation(
            summary = "Listar todas las evaluaciones",
            description = "Obtiene el registro histórico completo de calificaciones del colegio"
    )
    @GetMapping
    public ResponseEntity<List<Evaluacion>> listarTodas() {
        return ResponseEntity.ok(
                evaluacionService.listarTodas()
        );
    }

    /**
     * Endpoint para consultar todas las evaluaciones
     * correspondientes a un estudiante.
     *
     * @param estudianteId Identificador único del estudiante.
     * @return ResponseEntity con las evaluaciones del estudiante
     *         y código HTTP 200 (OK).
     */
    @Operation(
            summary = "Listar evaluaciones por estudiante",
            description = "Obtiene todas las calificaciones asociadas a un estudiante"
    )
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Evaluacion>> listarPorEstudiante(
            @PathVariable Long estudianteId
    ) {
        return ResponseEntity.ok(
                evaluacionService.listarPorEstudiante(
                        estudianteId
                )
        );
    }

    /**
     * Endpoint para consultar todas las evaluaciones
     * registradas en una asignatura.
     *
     * @param asignaturaId Identificador único de la asignatura.
     * @return ResponseEntity con las evaluaciones de la asignatura
     *         y código HTTP 200 (OK).
     */
    @Operation(
            summary = "Listar evaluaciones por asignatura",
            description = "Obtiene las calificaciones N1, N2 y N3 registradas para una asignatura"
    )
    @GetMapping("/asignatura/{asignaturaId}")
    public ResponseEntity<List<Evaluacion>> listarPorAsignatura(
            @PathVariable Long asignaturaId
    ) {
        return ResponseEntity.ok(
                evaluacionService.listarPorAsignatura(
                        asignaturaId
                )
        );
    }

    /**
     * Endpoint para consultar las evaluaciones de un estudiante
     * dentro de una asignatura específica.
     *
     * @param estudianteId Identificador único del estudiante.
     * @param asignaturaId Identificador único de la asignatura.
     * @return ResponseEntity con las evaluaciones encontradas
     *         y código HTTP 200 (OK).
     */
    @Operation(
            summary = "Listar evaluaciones por estudiante y asignatura",
            description = "Obtiene las evaluaciones N1, N2 y N3 de un estudiante en una asignatura específica"
    )
    @GetMapping(
            "/estudiante/{estudianteId}/asignatura/{asignaturaId}"
    )
    public ResponseEntity<List<Evaluacion>>
    listarPorEstudianteYAsignatura(
            @PathVariable Long estudianteId,
            @PathVariable Long asignaturaId
    ) {
        return ResponseEntity.ok(
                evaluacionService
                        .listarPorEstudianteYAsignatura(
                                estudianteId,
                                asignaturaId
                        )
        );
    }

    /**
     * Endpoint para registrar o actualizar una evaluación individual.
     * El registro es identificado por estudiante, asignatura
     * y número de evaluación N1, N2 o N3.
     *
     * @param evaluacion Evaluación validada que será registrada o actualizada.
     * @return ResponseEntity con la evaluación persistida
     *         y código HTTP 201 (CREATED).
     */
    @Operation(
            summary = "Registrar evaluación individual",
            description = "Registra o actualiza una calificación N1, N2 o N3 para un estudiante"
    )
    @PostMapping
    public ResponseEntity<Evaluacion> guardar(
            @Valid @RequestBody Evaluacion evaluacion
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        evaluacionService.guardar(evaluacion)
                );
    }

    /**
     * Endpoint para sincronizar una lista completa de evaluaciones.
     * Cada registro N1, N2 o N3 es creado cuando no existe
     * o actualizado cuando ya se encuentra almacenado.
     *
     * @param evaluaciones Lista de evaluaciones a sincronizar.
     * @return ResponseEntity con las evaluaciones creadas o actualizadas
     *         y código HTTP 200 (OK).
     */
    @Operation(
            summary = "Sincronizar lista de evaluaciones",
            description = "Registra o actualiza las calificaciones N1, N2 y N3 enviadas por el docente"
    )
    @PutMapping("/lista")
    public ResponseEntity<List<Evaluacion>> guardarLista(
            @Valid @RequestBody List<Evaluacion> evaluaciones
    ) {
        return ResponseEntity.ok(
                evaluacionService.guardarLista(
                        evaluaciones
                )
        );
    }

    /**
     * Endpoint para calcular el promedio de las evaluaciones
     * de un estudiante en una asignatura específica.
     *
     * @param estudianteId Identificador único del estudiante.
     * @param asignaturaId Identificador único de la asignatura.
     * @return ResponseEntity con el promedio calculado
     *         y código HTTP 200 (OK).
     */
    @Operation(
            summary = "Calcular promedio por asignatura",
            description = "Calcula el promedio aritmético de las evaluaciones de un estudiante en una asignatura"
    )
    @GetMapping("/estudiante/{estudianteId}/promedio")
    public ResponseEntity<Double> obtenerPromedio(
            @PathVariable Long estudianteId,
            @RequestParam Long asignaturaId
    ) {
        return ResponseEntity.ok(
                evaluacionService.calcularPromedioAsignatura(
                        estudianteId,
                        asignaturaId
                )
        );
    }
}