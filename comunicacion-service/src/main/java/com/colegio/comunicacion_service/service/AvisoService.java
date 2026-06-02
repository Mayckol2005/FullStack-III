package com.colegio.comunicacion_service.service;

import com.colegio.comunicacion_service.entity.Aviso;
import com.colegio.comunicacion_service.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio asociada a los comunicados y avisos institucionales.
 * Se conecta con AvisoRepository para listar y almacenar las publicaciones del mural de novedades.
 */
@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    /**
     * Obtiene una lista con todos los avisos institucionales publicados en el sistema.
     * @return Lista de objetos Aviso con la información del mural.
     */
    public List<Aviso> listarTodos() {
        return avisoRepository.findAll();
    }

    /**
     * Registra y publica un nuevo aviso en la base de datos institucional.
     * @param aviso Objeto Aviso que contiene el título, contenido y remitente.
     * @return El aviso guardado con su identificador único y fecha de publicación asignada.
     */
    public Aviso guardar(Aviso aviso) {
        return avisoRepository.save(aviso);
    }
}