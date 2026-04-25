package com.colegio.anotacion_service.service;

import com.colegio.anotacion_service.entity.Anotacion;
import com.colegio.anotacion_service.repository.AnotacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnotacionService {

    @Autowired
    private AnotacionRepository anotacionRepository;

    public List<Anotacion> listarTodas() {
        return anotacionRepository.findAll();
    }

    public List<Anotacion> listarPorEstudiante(Long estudianteId) {
        return anotacionRepository.findByEstudianteId(estudianteId);
    }

    public Anotacion guardar(Anotacion anotacion) {
        return anotacionRepository.save(anotacion);
    }
}