package com.colegio.comunicacion_service.controller;

import com.colegio.comunicacion_service.entity.Aviso;
import com.colegio.comunicacion_service.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunicaciones/avisos")
@CrossOrigin(origins = "*")
public class AvisoController {

    @Autowired
    private AvisoRepository avisoRepository;

    //Obtener todos los avisos
    @GetMapping
    public List<Aviso> listarAvisos() {
        return avisoRepository.findAll();
    }

    //Crear un nuevo aviso
    @PostMapping("/publicar")
    public ResponseEntity<Aviso> publicarAviso(@RequestBody Aviso aviso) {
        Aviso nuevoAviso = avisoRepository.save(aviso);
        return ResponseEntity.ok(nuevoAviso);
    }

    //Eliminar un aviso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAviso(@PathVariable Long id) {
        avisoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}