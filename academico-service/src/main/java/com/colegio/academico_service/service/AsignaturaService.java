package com.colegio.academico_service.service;

import com.colegio.academico_service.entity.Asignatura;
import com.colegio.academico_service.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<Asignatura> listarTodas() {
        return asignaturaRepository.findAll();
    }

    public Asignatura guardar(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public List<Asignatura> listarPorCurso(Long cursoId) {
        return asignaturaRepository.findByCursoId(cursoId);
    }
}