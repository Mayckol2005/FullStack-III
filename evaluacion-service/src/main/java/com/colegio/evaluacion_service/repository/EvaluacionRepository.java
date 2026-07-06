package com.colegio.evaluacion_service.repository;

import com.colegio.evaluacion_service.entity.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    List<Evaluacion> findByEstudianteId(
            Long estudianteId
    );

    List<Evaluacion> findByAsignaturaId(
            Long asignaturaId
    );

    List<Evaluacion> findByEstudianteIdAndAsignaturaId(
            Long estudianteId,
            Long asignaturaId
    );

    Optional<Evaluacion> findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
            Long estudianteId,
            Long asignaturaId,
            Integer numeroEvaluacion
    );
}