package com.colegio.academico_service.controller;

import com.colegio.academico_service.entity.Asignatura;
import com.colegio.academico_service.service.AsignaturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/academico/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping
    public ResponseEntity<List<Asignatura>> listarAsignaturas() {
        return ResponseEntity.ok(asignaturaService.listarTodas());
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Asignatura>> listarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(asignaturaService.listarPorCurso(cursoId));
    }

    @PostMapping
    public ResponseEntity<Asignatura> crearAsignatura(@Valid @RequestBody Asignatura asignatura) {
        return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaService.guardar(asignatura));
    }
}