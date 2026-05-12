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

    public Estudiante registrarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> obtenerPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    public List<Estudiante> obtenerPorCurso(Long cursoId) {
        return estudianteRepository.findByCursoId(cursoId);
    }

    // --- NUEVO: MÉTODO PARA ACTUALIZAR ---
    public Estudiante actualizarEstudiante(Long id, Estudiante detallesEstudiante) {
        return estudianteRepository.findById(id).map(estudianteExistente -> {
            estudianteExistente.setRut(detallesEstudiante.getRut());
            estudianteExistente.setNombres(detallesEstudiante.getNombres());
            estudianteExistente.setApellidos(detallesEstudiante.getApellidos());
            estudianteExistente.setEmail(detallesEstudiante.getEmail());
            estudianteExistente.setFechaNacimiento(detallesEstudiante.getFechaNacimiento());
            estudianteExistente.setCursoId(detallesEstudiante.getCursoId());
            estudianteExistente.setEstado(detallesEstudiante.getEstado());
            return estudianteRepository.save(estudianteExistente);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado con el ID: " + id));
    }

    // --- NUEVO: MÉTODO PARA ELIMINAR ---
    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }
}