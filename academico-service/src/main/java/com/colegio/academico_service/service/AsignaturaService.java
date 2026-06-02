package com.colegio.academico_service.service;

import com.colegio.academico_service.entity.Asignatura;
import com.colegio.academico_service.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio encargado de la lógica de negocio relacionada con las asignaturas.
 * Define las operaciones para listar y guardar las materias impartidas.
 */
@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    /**
     * Obtiene el catálogo completo de asignaturas del colegio.
     * @return Lista de todas las asignaturas.
     */
    public List<Asignatura> listarTodas() {
        return asignaturaRepository.findAll();
    }

    /**
     * Registra una nueva asignatura en el sistema.
     * @param asignatura Objeto Asignatura con los datos a guardar.
     * @return La asignatura guardada exitosamente.
     */
    public Asignatura guardar(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    /**
     * Obtiene todas las asignaturas que pertenecen a un curso específico.
     * @param cursoId Identificador único del curso.
     * @return Lista de asignaturas asociadas al curso.
     */
    public List<Asignatura> listarPorCurso(Long cursoId) {
        return asignaturaRepository.findByCursoId(cursoId);
    }
}