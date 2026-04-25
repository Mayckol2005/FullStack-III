package com.colegio.comunicacion_service.controller;

import com.colegio.comunicacion_service.entity.Aviso;
import com.colegio.comunicacion_service.service.AvisoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunicaciones")
public class AvisoController {

    @Autowired
    private AvisoService avisoService;

    @GetMapping
    public ResponseEntity<List<Aviso>> listarAvisos() {
        return ResponseEntity.ok(avisoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Aviso> crearAviso(@Valid @RequestBody Aviso aviso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avisoService.guardar(aviso));
    }
}