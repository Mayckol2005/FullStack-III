package com.colegio.anotacion_service.service;

import com.colegio.anotacion_service.entity.Anotacion;
import com.colegio.anotacion_service.repository.AnotacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio del libro de clases virtual (hoja de vida).
 * Interactúa con AnotacionRepository para consultar y persistir observaciones conductuales.
 */
@Service
public class AnotacionService {

    @Autowired
    private AnotacionRepository anotacionRepository;

    /**
     * Obtiene el historial completo de todas las anotaciones registradas en el colegio.
     * @return Lista de todas las observaciones (positivas, negativas, etc.).
     */
    public List<Anotacion> listarTodas() {
        return anotacionRepository.findAll();
    }

    /**
     * Obtiene la hoja de vida de un alumno específico.
     * @param estudianteId Identificador único del estudiante.
     * @return Lista de anotaciones asociadas a ese alumno.
     */
    public List<Anotacion> listarPorEstudiante(Long estudianteId) {
        return anotacionRepository.findByEstudianteId(estudianteId);
    }

    /**
     * Registra una nueva anotación en el sistema.
     * @param anotacion Objeto Anotacion con los detalles de la observación.
     * @return La anotación guardada con su ID autogenerado.
     */
    public Anotacion guardar(Anotacion anotacion) {
        return anotacionRepository.save(anotacion);
    }
}