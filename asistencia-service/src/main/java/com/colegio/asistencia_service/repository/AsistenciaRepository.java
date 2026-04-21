package com.colegio.asistencia_service.repository;

import com.colegio.asistencia_service.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    // Buscar todas las asistencias de un alumno específico
    List<Asistencia> findByEstudianteId(Long estudianteId);

    // Buscar la asistencia de un día específico
    List<Asistencia> findByFecha(LocalDate fecha);
}