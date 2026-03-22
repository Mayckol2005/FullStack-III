package com.colegio.academico_service.controller;

import com.colegio.academico_service.entity.Asignatura;
import com.colegio.academico_service.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academico/asignaturas")
@CrossOrigin(origins = "*")
public class AsignaturaController {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @GetMapping
    public List<Asignatura> listarAsignaturas() {
        return asignaturaRepository.findAll();
    }

    @PostMapping("/guardar")
    public ResponseEntity<Asignatura> guardarAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura nuevaAsignatura = asignaturaRepository.save(asignatura);
        return ResponseEntity.ok(nuevaAsignatura);
    }
}