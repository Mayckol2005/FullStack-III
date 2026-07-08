package com.colegio.comunicacion_service.service;

import com.colegio.comunicacion_service.entity.Aviso;
import com.colegio.comunicacion_service.repository.AvisoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        // Guardamos primero en la base de datos relacional
        Aviso avisoGuardado = avisoRepository.save(aviso);

        // Dispara el evento asíncrono: Publicamos el título en la cola
        String mensaje = "Nuevo aviso publicado: " + avisoGuardado.getTitulo();
        rabbitTemplate.convertAndSend("notificaciones_queue", mensaje);

        return avisoGuardado;
    }

    public void eliminar(Long id) {
        avisoRepository.deleteById(id);
    }
}
