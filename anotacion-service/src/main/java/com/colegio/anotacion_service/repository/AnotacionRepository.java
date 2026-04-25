package com.colegio.anotacion_service.repository;

import com.colegio.anotacion_service.entity.Anotacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnotacionRepository extends JpaRepository<Anotacion, Long> {
    List<Anotacion> findByEstudianteId(Long estudianteId);
}