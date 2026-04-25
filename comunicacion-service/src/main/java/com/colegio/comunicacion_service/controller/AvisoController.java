package com.colegio.comunicacion_service.controller;

import com.colegio.comunicacion_service.entity.Aviso;
import com.colegio.comunicacion_service.service.AvisoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunicaciones")
@Tag(name = "Mural de Avisos", description = "Endpoints para la publicación de circulares y noticias")
public class AvisoController {

    @Autowired
    private AvisoService avisoService;

    @Operation(summary = "Ver mural general", description = "Devuelve la lista de todos los comunicados emitidos por el colegio")
    @GetMapping
    public ResponseEntity<List<Aviso>> listarAvisos() {
        return ResponseEntity.ok(avisoService.listarTodos());
    }

    @Operation(summary = "Publicar nuevo aviso", description = "Crea un nuevo comunicado dirigido a un curso o nivel específico")
    @PostMapping
    public ResponseEntity<Aviso> crearAviso(@Valid @RequestBody Aviso aviso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avisoService.guardar(aviso));
    }
}