package com.colegio.asistencia_service.service;

import com.colegio.asistencia_service.entity.Asistencia;
import com.colegio.asistencia_service.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio para el control de asistencia.
 * Se comunica con AsistenciaRepository para almacenar y consultar la presencia o ausencia de los alumnos.
 */
@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    /**
     * Obtiene el registro histórico completo de todas las asistencias tomadas en el colegio.
     * @return Lista de objetos Asistencia.
     */
    public List<Asistencia> listarTodas() {
        return asistenciaRepository.findAll();
    }

    /**
     * Registra y guarda la asistencia diaria de un estudiante.
     * @param asistencia Objeto Asistencia que contiene fecha, alumno y estado (presente/ausente).
     * @return El registro de asistencia guardado con su ID generado.
     */
    public Asistencia guardar(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }
}