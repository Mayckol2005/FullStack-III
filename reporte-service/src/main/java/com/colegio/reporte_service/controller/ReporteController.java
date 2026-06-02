package com.colegio.reporte_service.controller;

import com.colegio.reporte_service.dto.ReporteGeneralDto;
import com.colegio.reporte_service.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que expone endpoints para la generación de reportes y estadísticas.
 * Actúa como un punto de acceso único para la consulta de información consolidada del colegio.
 */
@RestController
@RequestMapping("/api/reportes")
@Tag(name = "Generador de Reportes", description = "Endpoints para la consolidación de estadísticas del colegio")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    /**
     * Endpoint para obtener el boletín general consolidado del colegio.
     * @return ResponseEntity con el objeto ReporteGeneralDto y código HTTP 200 (OK).
     */
    @Operation(summary = "Obtener Boletín General", description = "Consulta a múltiples microservicios para armar un informe general del colegio")
    @GetMapping("/general")
    public ResponseEntity<ReporteGeneralDto> obtenerReporteGeneral() {
        return ResponseEntity.ok(reporteService.obtenerReporteGeneral());
    }
}