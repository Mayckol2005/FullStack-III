package com.colegio.comunicacion_service.service;

import com.colegio.comunicacion_service.entity.Aviso;
import com.colegio.comunicacion_service.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    public List<Aviso> listarTodos() {
        return avisoRepository.findAll();
    }

    public Aviso guardar(Aviso aviso) {
        return avisoRepository.save(aviso);
    }
}