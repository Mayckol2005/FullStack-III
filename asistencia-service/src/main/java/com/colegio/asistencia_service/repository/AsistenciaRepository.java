package com.colegio.asistencia_service.repository;

import com.colegio.asistencia_service.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    List<Asistencia> findByEstudianteId(Long estudianteId);

    List<Asistencia> findByFecha(LocalDate fecha);

    List<Asistencia> findByCursoIdAndFecha(
            Long cursoId,
            LocalDate fecha
    );

    Optional<Asistencia> findByEstudianteIdAndFecha(
            Long estudianteId,
            LocalDate fecha
    );
}