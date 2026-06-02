package com.colegio.notificacion_service.service;

import com.colegio.notificacion_service.dto.MensajeDto;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de la lógica de envío de notificaciones y alertas.
 * Procesa la información de los correos electrónicos para despacharlos a la comunidad escolar.
 */
@Service
public class NotificacionService {

    /**
     * Simula el envío de un correo electrónico a través de la consola del sistema.
     * En un entorno de producción, este método se integraría con un servidor SMTP (ej. JavaMailSender).
     * @param mensaje Objeto MensajeDto que contiene el destinatario, el asunto y el cuerpo del correo.
     * @return Un String confirmando el éxito de la operación y el correo del destinatario.
     */
    public String enviarCorreo(MensajeDto mensaje) {
        System.out.println("-------------------------------------------------");
        System.out.println("📧 ENVIANDO CORREO ELECTRÓNICO...");
        System.out.println("Destinatario: " + mensaje.getDestinatario());
        System.out.println("Asunto: " + mensaje.getAsunto());
        System.out.println("Contenido: " + mensaje.getContenido());
        System.out.println("-------------------------------------------------");

        return "Notificación enviada con éxito a: " + mensaje.getDestinatario();
    }
}