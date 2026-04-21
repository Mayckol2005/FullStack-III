package com.colegio.asistencia_service.controller;

import com.colegio.asistencia_service.entity.Asistencia;
import com.colegio.asistencia_service.repository.AsistenciaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaRepository repository;

    // Registrar asistencia
    @PostMapping
    public ResponseEntity<Asistencia> registrarAsistencia(@Valid @RequestBody Asistencia asistencia) {
        Asistencia nuevaAsistencia = repository.save(asistencia);
        return new ResponseEntity<>(nuevaAsistencia, HttpStatus.CREATED);
    }

    // Ver historial de un estudiante
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Asistencia>> obtenerPorEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(repository.findByEstudianteId(estudianteId));
    }

    // Listado general de asistencia
    @GetMapping
    public ResponseEntity<List<Asistencia>> listarTodo() {
        return ResponseEntity.ok(repository.findAll());
    }
}