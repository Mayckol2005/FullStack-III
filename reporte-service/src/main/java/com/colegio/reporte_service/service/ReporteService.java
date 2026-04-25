package com.colegio.reporte_service.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReporteService {

    public Map<String, Object> obtenerReporteGeneral() {
        Map<String, Object> reporte = new HashMap<>();

        reporte.put("totalEstudiantes", 450);
        reporte.put("promedioGeneralColegio", 5.8);
        reporte.put("asistenciaPromedio", "92%");
        reporte.put("estado", "Arquitectura refactorizada y lista para OpenFeign 🚀");

        return reporte;
    }
}