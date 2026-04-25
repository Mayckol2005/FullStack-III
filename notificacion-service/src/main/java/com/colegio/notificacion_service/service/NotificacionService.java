package com.colegio.notificacion_service.service;

import com.colegio.notificacion_service.dto.MensajeDto;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

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