package com.colegio.evaluacion_service.repository;

import com.colegio.evaluacion_service.entity.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByEstudianteId(Long estudianteId);
}