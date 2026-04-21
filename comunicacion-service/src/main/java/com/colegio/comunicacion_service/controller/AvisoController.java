package com.colegio.comunicacion_service.controller;

import com.colegio.comunicacion_service.entity.Aviso;
import com.colegio.comunicacion_service.repository.AvisoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunicaciones/avisos")
@CrossOrigin(origins = "*")
@Tag(name = "Portal de Comunicaciones", description = "Endpoints para la publicación y lectura de avisos institucionales")
public class AvisoController {

    @Autowired
    private AvisoRepository avisoRepository;

    @Operation(summary = "Listar todos los avisos", description = "Recupera el historial completo de comunicados institucionales.")
    @GetMapping
    public List<Aviso> listarAvisos() {
        return avisoRepository.findAll();
    }

    @Operation(summary = "Publicar un nuevo aviso", description = "Valida y guarda un nuevo comunicado en la base de datos.")
    @PostMapping("/publicar")
    public ResponseEntity<Aviso> publicarAviso(@Valid @RequestBody Aviso aviso) {
        Aviso nuevoAviso = avisoRepository.save(aviso);
        return ResponseEntity.ok(nuevoAviso);
    }

    @Operation(summary = "Eliminar un aviso", description = "Borra un comunicado existente mediante su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAviso(@PathVariable Long id) {
        if (avisoRepository.existsById(id)) {
            avisoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}