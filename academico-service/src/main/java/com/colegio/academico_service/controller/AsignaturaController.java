package com.colegio.academico_service.controller;

import com.colegio.academico_service.entity.Asignatura;
import com.colegio.academico_service.service.AsignaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/academico/asignaturas")
@Tag(name = "Plan de Estudio", description = "Endpoints para gestionar las asignaturas impartidas en el colegio")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @Operation(summary = "Listar todas las materias", description = "Obtiene el catálogo completo de asignaturas del colegio")
    @GetMapping
    public ResponseEntity<List<Asignatura>> listarAsignaturas() {
        return ResponseEntity.ok(asignaturaService.listarTodas());
    }

    @Operation(summary = "Materias por curso", description = "Filtra y devuelve las asignaturas asignadas a un curso en particular")
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Asignatura>> listarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(asignaturaService.listarPorCurso(cursoId));
    }

    @Operation(summary = "Crear asignatura", description = "Registra una nueva materia y la asocia a un curso y a un profesor")
    @PostMapping
    public ResponseEntity<Asignatura> crearAsignatura(@Valid @RequestBody Asignatura asignatura) {
        return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaService.guardar(asignatura));
    }
}