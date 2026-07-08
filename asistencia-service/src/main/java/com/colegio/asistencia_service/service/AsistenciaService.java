package com.colegio.asistencia_service.service;

import com.colegio.asistencia_service.entity.Asistencia;
import com.colegio.asistencia_service.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio para el control de asistencia.
 * Se comunica con AsistenciaRepository para almacenar, actualizar y consultar
 * la presencia o ausencia diaria de los estudiantes.
 */
@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    /**
     * Obtiene el registro histórico completo de todas las asistencias tomadas en el colegio.
     *
     * @return Lista de objetos Asistencia.
     */
    public List<Asistencia> listarTodas() {
        return asistenciaRepository.findAll();
    }

    /**
     * Obtiene los registros de asistencia asociados a un curso y una fecha específica.
     *
     * @param cursoId Identificador único del curso.
     * @param fecha Fecha correspondiente a la toma de asistencia.
     * @return Lista de asistencias registradas para el curso y fecha indicados.
     */
    public List<Asistencia> listarPorCursoYFecha(
            Long cursoId,
            LocalDate fecha
    ) {
        return asistenciaRepository.findByCursoIdAndFecha(cursoId, fecha);
    }

    public List<Asistencia> listarPorEstudiante(Long estudianteId) {
        return asistenciaRepository.findByEstudianteId(estudianteId);
    }

    /**
     * Registra o actualiza la asistencia diaria de un estudiante.
     * Si el estudiante ya posee un registro para la fecha indicada,
     * se actualizan sus datos. En caso contrario, se crea un nuevo registro.
     *
     * @param asistencia Objeto Asistencia que contiene fecha, curso, estudiante,
     *                   estado de presencia y observación.
     * @return El registro de asistencia creado o actualizado.
     */
    @Transactional
    public Asistencia guardar(Asistencia asistencia) {
        return asistenciaRepository
                .findByEstudianteIdAndFecha(
                        asistencia.getEstudianteId(),
                        asistencia.getFecha()
                )
                .map(asistenciaExistente -> {
                    asistenciaExistente.setCursoId(asistencia.getCursoId());
                    asistenciaExistente.setPresente(asistencia.isPresente());
                    asistenciaExistente.setObservacion(asistencia.getObservacion());

                    return asistenciaRepository.save(asistenciaExistente);
                })
                .orElseGet(() -> asistenciaRepository.save(asistencia));
    }

    /**
     * Registra o actualiza una lista completa de asistencias.
     * Cada elemento es procesado de forma individual para determinar si
     * corresponde crear un nuevo registro o actualizar uno existente.
     *
     * @param asistencias Lista de registros de asistencia a sincronizar.
     * @return Lista de asistencias creadas o actualizadas.
     */
    @Transactional
    public List<Asistencia> guardarLista(List<Asistencia> asistencias) {
        return asistencias.stream()
                .map(this::guardar)
                .toList();
    }
}
