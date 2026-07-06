package com.colegio.evaluacion_service.service;

import com.colegio.evaluacion_service.entity.Evaluacion;
import com.colegio.evaluacion_service.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio asociada
 * al registro y consulta de calificaciones de los estudiantes.
 * Permite administrar las evaluaciones N1, N2 y N3 de cada
 * asignatura y calcular sus respectivos promedios.
 */
@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    /**
     * Obtiene el listado completo de evaluaciones registradas
     * en el establecimiento.
     *
     * @return Lista de todas las evaluaciones almacenadas.
     */
    public List<Evaluacion> listarTodas() {
        return evaluacionRepository.findAll();
    }

    /**
     * Obtiene todas las evaluaciones asociadas a un estudiante.
     *
     * @param estudianteId Identificador único del estudiante.
     * @return Lista de evaluaciones pertenecientes al estudiante.
     */
    public List<Evaluacion> listarPorEstudiante(Long estudianteId) {
        return evaluacionRepository.findByEstudianteId(estudianteId);
    }

    /**
     * Obtiene todas las evaluaciones registradas para una asignatura.
     *
     * @param asignaturaId Identificador único de la asignatura.
     * @return Lista de evaluaciones pertenecientes a la asignatura.
     */
    public List<Evaluacion> listarPorAsignatura(Long asignaturaId) {
        return evaluacionRepository.findByAsignaturaId(asignaturaId);
    }

    /**
     * Obtiene las evaluaciones de un estudiante correspondientes
     * a una asignatura específica.
     *
     * @param estudianteId Identificador único del estudiante.
     * @param asignaturaId Identificador único de la asignatura.
     * @return Lista de evaluaciones del estudiante en la asignatura indicada.
     */
    public List<Evaluacion> listarPorEstudianteYAsignatura(
            Long estudianteId,
            Long asignaturaId
    ) {
        return evaluacionRepository
                .findByEstudianteIdAndAsignaturaId(
                        estudianteId,
                        asignaturaId
                );
    }

    /**
     * Registra o actualiza una evaluación de un estudiante.
     * La evaluación es identificada de forma lógica mediante el estudiante,
     * la asignatura y el número de evaluación N1, N2 o N3.
     * Si el registro ya existe, se actualizan la nota y la fecha.
     * En caso contrario, se crea una nueva evaluación.
     *
     * @param evaluacion Evaluación que contiene estudiante, asignatura,
     *                   número de evaluación, nota y fecha.
     * @return Evaluación creada o actualizada.
     */
    @Transactional
    public Evaluacion guardar(Evaluacion evaluacion) {
        LocalDate fechaRegistro = evaluacion.getFecha() != null
                ? evaluacion.getFecha()
                : LocalDate.now();

        evaluacion.setFecha(fechaRegistro);

        return evaluacionRepository
                .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                        evaluacion.getEstudianteId(),
                        evaluacion.getAsignaturaId(),
                        evaluacion.getNumeroEvaluacion()
                )
                .map(evaluacionExistente -> {
                    evaluacionExistente.setNota(
                            evaluacion.getNota()
                    );

                    evaluacionExistente.setFecha(
                            fechaRegistro
                    );

                    return evaluacionRepository.save(
                            evaluacionExistente
                    );
                })
                .orElseGet(
                        () -> evaluacionRepository.save(evaluacion)
                );
    }

    /**
     * Registra o actualiza una lista de evaluaciones.
     * Cada registro es procesado individualmente para determinar
     * si corresponde crear una evaluación o actualizar una existente.
     *
     * @param evaluaciones Lista de evaluaciones a sincronizar.
     * @return Lista de evaluaciones creadas o actualizadas.
     */
    @Transactional
    public List<Evaluacion> guardarLista(
            List<Evaluacion> evaluaciones
    ) {
        return evaluaciones.stream()
                .map(this::guardar)
                .toList();
    }

    /**
     * Calcula el promedio aritmético de las evaluaciones registradas
     * para un estudiante dentro de una asignatura.
     * El resultado es redondeado a un decimal.
     *
     * @param estudianteId Identificador único del estudiante.
     * @param asignaturaId Identificador único de la asignatura.
     * @return Promedio de las calificaciones o 0.0 cuando no existen notas.
     */
    public Double calcularPromedioAsignatura(
            Long estudianteId,
            Long asignaturaId
    ) {
        List<Evaluacion> evaluaciones =
                evaluacionRepository
                        .findByEstudianteIdAndAsignaturaId(
                                estudianteId,
                                asignaturaId
                        );

        if (evaluaciones.isEmpty()) {
            return 0.0;
        }

        double sumaNotas = evaluaciones.stream()
                .mapToDouble(Evaluacion::getNota)
                .sum();

        double promedio = sumaNotas / evaluaciones.size();

        return Math.round(promedio * 10.0) / 10.0;
    }
}