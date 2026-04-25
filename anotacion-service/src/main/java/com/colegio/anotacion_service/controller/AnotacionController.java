package com.colegio.anotacion_service.controller;

import com.colegio.anotacion_service.entity.Anotacion;
import com.colegio.anotacion_service.service.AnotacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anotaciones")
public class AnotacionController {

    @Autowired
    private AnotacionService anotacionService;

    @GetMapping
    public ResponseEntity<List<Anotacion>> listarTodas() {
        return ResponseEntity.ok(anotacionService.listarTodas());
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<Anotacion>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(anotacionService.listarPorEstudiante(id));
    }

    @PostMapping
    public ResponseEntity<Anotacion> guardar(@Valid @RequestBody Anotacion anotacion) {
        Anotacion nuevaAnotacion = anotacionService.guardar(anotacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAnotacion);
    }
}