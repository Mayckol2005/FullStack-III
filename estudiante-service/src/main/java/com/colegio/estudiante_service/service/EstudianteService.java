package com.colegio.estudiante_service.service;

import com.colegio.estudiante_service.entity.Estudiante;
import com.colegio.estudiante_service.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Método para matricular (guardar) un estudiante
    public Estudiante registrarEstudiante(Estudiante estudiante) {
        // Aquí en el futuro podrías agregar lógica para verificar si el RUT ya existe
        return estudianteRepository.save(estudiante);
    }

    // Método para obtener todos los estudiantes del colegio
    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    // Método para buscar a un estudiante por su ID
    public Optional<Estudiante> obtenerPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    // Método clave para la EV2: Buscar todos los alumnos de un curso (ej: 1ro Medio A)
    public List<Estudiante> obtenerPorCurso(Long cursoId) {
        return estudianteRepository.findByCursoId(cursoId);
    }
}