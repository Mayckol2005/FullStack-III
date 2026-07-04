package com.colegio.academico_service.service;

import com.colegio.academico_service.entity.Curso;
import com.colegio.academico_service.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de la gestión de cursos académicos dentro del establecimiento.
 * Interactúa con CursoRepository para realizar las operaciones de persistencia.
 */
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Obtiene una lista con todos los cursos registrados.
     * @return Lista de objetos Curso.
     */
    @Cacheable(value = "cursos")
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    /**
     * Guarda un nuevo curso en la base de datos.
     * @param curso Objeto Curso con los datos a registrar.
     * @return El curso guardado con su ID generado.
     */
    @CacheEvict(value = "cursos", allEntries = true)
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    /**
     * Busca un curso específico por su identificador único.
     * @param id Identificador del curso.
     * @return El objeto Curso si existe, o null si no se encuentra.
     */
    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }
}