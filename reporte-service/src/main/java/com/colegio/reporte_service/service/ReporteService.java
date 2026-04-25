package com.colegio.reporte_service.service;

import com.colegio.reporte_service.dto.ReporteGeneralDto;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    public ReporteGeneralDto obtenerReporteGeneral() {
        // En el próximo paso conectaremos OpenFeign aquí
        return new ReporteGeneralDto(
                450,
                5.8,
                "92%",
                "Arquitectura refactorizada y lista para OpenFeign 🚀"
        );
    }
}