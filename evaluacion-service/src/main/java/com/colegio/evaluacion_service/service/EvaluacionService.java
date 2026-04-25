package com.colegio.evaluacion_service.service;

import com.colegio.evaluacion_service.entity.Evaluacion;
import com.colegio.evaluacion_service.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> listarTodas() {
        return evaluacionRepository.findAll();
    }

    public List<Evaluacion> listarPorEstudiante(Long estudianteId) {
        return evaluacionRepository.findByEstudianteId(estudianteId);
    }

    public Evaluacion guardar(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

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