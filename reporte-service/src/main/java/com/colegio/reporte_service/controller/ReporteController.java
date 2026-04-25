package com.colegio.reporte_service.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @GetMapping("/general")
    public Map<String, Object> obtenerReporteGeneral() {
        Map<String, Object> reporte = new HashMap<>();

        reporte.put("totalEstudiantes", 450);
        reporte.put("promedioGeneralColegio", 5.8);
        reporte.put("asistenciaPromedio", "92%");
        reporte.put("estado", "El sistema de microservicios está funcionando al 100%");

        return reporte;
    }
}