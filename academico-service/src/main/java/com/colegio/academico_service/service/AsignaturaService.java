package com.colegio.academico_service.service;

import com.colegio.academico_service.entity.Asignatura;
import com.colegio.academico_service.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de la lógica de negocio relacionada con las asignaturas.
 * Define las operaciones para listar, guardar, editar y eliminar
 * las materias impartidas en el establecimiento.
 */
@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    /**
     * Obtiene el catálogo completo de asignaturas del colegio.
     *
     * @return Lista de todas las asignaturas.
     */
    public List<Asignatura> listarTodas() {
        return asignaturaRepository.findAll();
    }

    /**
     * Registra una nueva asignatura en el sistema.
     *
     * @param asignatura Objeto Asignatura con los datos a guardar.
     * @return La asignatura guardada exitosamente.
     */
    public Asignatura guardar(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    /**
     * Obtiene todas las asignaturas que pertenecen a un curso específico.
     *
     * @param cursoId Identificador único del curso.
     * @return Lista de asignaturas asociadas al curso.
     */
    public List<Asignatura> listarPorCurso(Long cursoId) {
        return asignaturaRepository.findByCursoId(cursoId);
    }

    /**
     * Obtiene todas las asignaturas impartidas por un docente específico.
     * La asociación se realiza mediante el identificador del usuario docente
     * almacenado en cada asignatura.
     *
     * @param docenteId Identificador único del docente.
     * @return Lista de asignaturas asociadas al docente indicado.
     */
    public List<Asignatura> listarPorDocente(Long docenteId) {
        return asignaturaRepository.findByDocenteId(docenteId);
    }

    /**
     * Actualiza los datos de una asignatura existente.
     *
     * @param id Identificador de la asignatura a modificar.
     * @param detallesAsignatura Objeto con los nuevos datos.
     * @return La asignatura actualizada, o null si no se encontró.
     */
    public Asignatura actualizar(
            Long id,
            Asignatura detallesAsignatura
    ) {
        return asignaturaRepository
                .findById(id)
                .map(asignaturaExistente -> {
                    asignaturaExistente.setNombre(
                            detallesAsignatura.getNombre()
                    );

                    asignaturaExistente.setCursoId(
                            detallesAsignatura.getCursoId()
                    );

                    asignaturaExistente.setDocenteId(
                            detallesAsignatura.getDocenteId()
                    );

                    return asignaturaRepository.save(
                            asignaturaExistente
                    );
                })
                .orElse(null);
    }

    /**
     * Elimina una asignatura del sistema.
     *
     * @param id Identificador de la asignatura a eliminar.
     * @return true si se eliminó correctamente, false si no se encontró.
     */
    public boolean eliminar(Long id) {
        if (asignaturaRepository.existsById(id)) {
            asignaturaRepository.deleteById(id);
            return true;
        }

        return false;
    }
}