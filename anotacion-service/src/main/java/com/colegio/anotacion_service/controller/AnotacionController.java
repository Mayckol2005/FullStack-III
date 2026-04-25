package com.colegio.anotacion_service.controller;

import com.colegio.anotacion_service.entity.Anotacion;
import com.colegio.anotacion_service.repository.AnotacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anotaciones")
public class AnotacionController {

    @Autowired
    private AnotacionRepository anotacionRepository;

    @GetMapping
    public List<Anotacion> listarTodas() {
        return anotacionRepository.findAll();
    }

    @GetMapping("/estudiante/{id}")
    public List<Anotacion> listarPorEstudiante(@PathVariable Long id) {
        return anotacionRepository.findByEstudianteId(id);
    }

    @PostMapping
    public Anotacion guardar(@RequestBody Anotacion anotacion) {
        return anotacionRepository.save(anotacion);
    }
}