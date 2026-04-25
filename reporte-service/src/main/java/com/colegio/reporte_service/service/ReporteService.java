package com.colegio.reporte_service.service;

import com.colegio.reporte_service.client.EstudianteClient;
import com.colegio.reporte_service.dto.ReporteGeneralDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private EstudianteClient estudianteClient;

    public ReporteGeneralDto obtenerReporteGeneral() {
        int totalReal = 0;
        String mensajeEstado = "Reporte sincronizado vía OpenFeign 🚀";

        try {
            List<Object> estudiantes = estudianteClient.listarTodos();
            totalReal = estudiantes.size();
        } catch (Exception e) {
            mensajeEstado = "Aviso: No se pudo conectar con el servicio de Estudiantes.";
        }

        return new ReporteGeneralDto(
                totalReal,
                5.8,
                "92%",
                mensajeEstado
        );
    }
}