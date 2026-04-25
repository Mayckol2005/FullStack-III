package com.colegio.reporte_service.controller;

import com.colegio.reporte_service.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/general")
    public ResponseEntity<Map<String, Object>> obtenerReporteGeneral() {
        return ResponseEntity.ok(reporteService.obtenerReporteGeneral());
    }
}