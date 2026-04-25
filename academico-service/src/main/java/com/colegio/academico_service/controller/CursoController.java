package com.colegio.academico_service.controller;

import com.colegio.academico_service.entity.Curso;
import com.colegio.academico_service.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academico/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id);
        if(curso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }
}