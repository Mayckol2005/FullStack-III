package com.colegio.evaluacion_service.service;

import com.colegio.evaluacion_service.entity.Evaluacion;
import com.colegio.evaluacion_service.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio encargado de la lógica de negocio para la gestión de calificaciones.
 * Procesa el registro de notas y realiza los cálculos matemáticos para los promedios.
 */
@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    /**
     * Obtiene el listado completo de todas las evaluaciones ingresadas en el colegio.
     * @return Lista de objetos Evaluacion con las notas de todos los alumnos.
     */
    public List<Evaluacion> listarTodas() {
        return evaluacionRepository.findAll();
    }

    /**
     * Busca y retorna todas las calificaciones correspondientes a un estudiante específico.
     * @param estudianteId Identificador único del alumno.
     * @return Lista de evaluaciones filtrada por el ID del estudiante.
     */
    public List<Evaluacion> listarPorEstudiante(Long estudianteId) {
        return evaluacionRepository.findByEstudianteId(estudianteId);
    }

    /**
     * Registra una nueva calificación en el sistema.
     * @param evaluacion Objeto Evaluacion con la nota, asignatura y estudiante asociado.
     * @return La evaluación guardada en la base de datos con su identificador generado.
     */
    public Evaluacion guardar(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    /**
     * Calcula el promedio aritmético de un estudiante en una asignatura específica.
     * Filtra las notas del alumno por materia, realiza la sumatoria y redondea a un decimal.
     * @param estudianteId Identificador único del alumno.
     * @param asignatura Nombre de la materia (ej. "Matemáticas").
     * @return El promedio calculado (ej. 5.5). Retorna 0.0 si el estudiante no tiene notas registradas en dicha materia.
     */
    public Double calcularPromedioAsignatura(Long estudianteId, String asignatura) {
        List<Evaluacion> notas = evaluacionRepository.findByEstudianteId(estudianteId)
                .stream()
                .filter(e -> e.getAsignatura().equalsIgnoreCase(asignatura))
                .collect(Collectors.toList());

        // Si no tiene notas, devolvemos 0.0
        if (notas.isEmpty()) {
            return 0.0;
        }

        double sumaNotas = 0.0;

        for (Evaluacion e : notas) {
            sumaNotas += e.getNota();
        }

        double promedioFinal = sumaNotas / notas.size();

        // Redondeamos a un decimal (ej: 5.46 -> 5.5)
        return Math.round(promedioFinal * 10.0) / 10.0;
    }
}