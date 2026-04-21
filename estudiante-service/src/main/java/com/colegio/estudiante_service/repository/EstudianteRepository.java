package com.colegio.estudiante_service.repository;

import com.colegio.estudiante_service.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Spring Data hace la magia: si le pones "findBy" + el nombre del campo, te crea la consulta SQL automática.
    Optional<Estudiante> findByRut(String rut);
    Optional<Estudiante> findByEmail(String email);

    // Para buscar a todos los alumnos de un curso específico
    List<Estudiante> findByCursoId(Long cursoId);
}