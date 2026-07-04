package com.colegio.comunicacion_service.service;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacionListener {

    /**
     * Escucha activamente la cola de notificaciones y procesa los mensajes entrantes.
     * La cola se creará de forma dinámica en RabbitMQ si no existe al iniciar el servicio.
     * @param mensaje Cadena de texto enviada por el AvisoService.
     */
    @RabbitListener(queuesToDeclare = @Queue("notificaciones_queue"))
    public void procesarNotificacion(String mensaje) {
        System.out.println("=========================================================");
        System.out.println("📩 [RabbitMQ CONSUMER] Procesando tarea en segundo plano...");
        System.out.println("📩 Mensaje recibido con éxito: " + mensaje);
        System.out.println("=========================================================");
    }
}