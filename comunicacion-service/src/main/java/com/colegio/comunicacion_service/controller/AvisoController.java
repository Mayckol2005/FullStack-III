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

/**
 * Controlador REST que expone los endpoints públicos e internos para el Mural de Avisos.
 * Permite la consulta general de novedades y la creación de nuevas circulares informativas.
 */
@RestController
@RequestMapping("/api/comunicaciones")
@Tag(name = "Mural de Avisos", description = "Endpoints para la publicación de circulares y noticias")
public class AvisoController {

    @Autowired
    private AvisoService avisoService;

    /**
     * Endpoint para consultar el listado completo de avisos vigentes en el mural.
     * @return ResponseEntity con la lista de avisos encontrados y código HTTP 200 (OK).
     */
    @Operation(summary = "Ver mural general", description = "Devuelve la lista de todos los comunicados emitidos por el colegio")
    @GetMapping
    public ResponseEntity<List<Aviso>> listarAvisos() {
        return ResponseEntity.ok(avisoService.listarTodos());
    }

    /**
     * Endpoint para publicar una nueva circular o noticia en el mural.
     * @param aviso Objeto JSON validado con la estructura necesaria para generar un aviso.
     * @return ResponseEntity con el objeto de la comunicación guardada y código HTTP 201 (CREATED).
     */
    @Operation(summary = "Publicar nuevo aviso", description = "Crea un nuevo comunicado dirigido a un curso o nivel específico")
    @PostMapping
    public ResponseEntity<Aviso> crearAviso(@Valid @RequestBody Aviso aviso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avisoService.guardar(aviso));
    }

    @Operation(summary = "Eliminar aviso", description = "Elimina un comunicado institucional por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAviso(@PathVariable Long id) {
        avisoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
