package com.colegio.estudiante_service.service;

import com.colegio.estudiante_service.entity.Estudiante;
import com.colegio.estudiante_service.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar la lógica de negocio para los estudiantes.
 * Interactúa directamente con EstudianteRepository para las operaciones CRUD.
 */
@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    /**
     * Registra un nuevo estudiante en el sistema.
     * @param estudiante Objeto Estudiante con los datos a guardar.
     * @return El estudiante guardado con su ID generado.
     */
    public Estudiante registrarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    /**
     * Obtiene la lista completa de estudiantes matriculados.
     * @return Lista de objetos Estudiante.
     */
    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    /**
     * Busca un estudiante por su identificador único.
     * @param id Identificador del estudiante.
     * @return Un Optional que contiene el estudiante si se encuentra, o vacío si no existe.
     */
    public Optional<Estudiante> obtenerPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    /**
     * Obtiene todos los estudiantes asociados a un curso específico.
     * @param cursoId Identificador del curso.
     * @return Lista de estudiantes pertenecientes al curso.
     */
    public List<Estudiante> obtenerPorCurso(Long cursoId) {
        return estudianteRepository.findByCursoId(cursoId);
    }

    /**
     * Actualiza los datos de un estudiante existente.
     * @param id Identificador del estudiante a actualizar.
     * @param detallesEstudiante Objeto con los nuevos datos.
     * @return El estudiante actualizado.
     * @throws RuntimeException Si el estudiante no es encontrado en la base de datos.
     */
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

    /**
     * Elimina un estudiante del sistema por su ID.
     * @param id Identificador del estudiante a eliminar.
     */
    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }
}