package com.colegio.academico_service.service;

import com.colegio.academico_service.entity.Curso;
import com.colegio.academico_service.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }
}