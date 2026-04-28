package com.colegio.reporte_service.service;

import com.colegio.reporte_service.client.EstudianteClient;
import com.colegio.reporte_service.client.EvaluacionClient;
import com.colegio.reporte_service.dto.ReporteGeneralDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {

    @Autowired
    private EstudianteClient estudianteClient;

    @Autowired
    private EvaluacionClient evaluacionClient;

    public ReporteGeneralDto obtenerReporteGeneral() {
        int totalReal = 0;
        double promedioReal = 0.0;
        String mensajeEstado = "Reporte BFF sincronizado con Estudiantes y Notas 🚀";

        // 1. Llamar a Estudiantes
        try {
            List<Object> estudiantes = estudianteClient.listarTodos();
            totalReal = estudiantes.size();
        } catch (Exception e) {
            mensajeEstado = "Aviso: Estudiante-Service no responde.";
        }

        // 2. Llamar a Evaluaciones y calcular promedio
        try {
            List<Map<String, Object>> notas = evaluacionClient.listarTodas();
            if (!notas.isEmpty()) {
                double suma = 0.0;
                for (Map<String, Object> notaObj : notas) {
                    // Extraemos la nota del JSON que nos responde el otro microservicio
                    suma += Double.parseDouble(notaObj.get("nota").toString());
                }
                // Calculamos el promedio y lo redondeamos a 1 decimal
                promedioReal = Math.round((suma / notas.size()) * 10.0) / 10.0;
            }
        } catch (Exception e) {
            mensajeEstado = "Aviso: Evaluacion-Service no responde. Promedio en 0.";
        }

        return new ReporteGeneralDto(
                totalReal,
                promedioReal,
                "92%",
                mensajeEstado
        );
    }
}