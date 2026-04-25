package com.colegio.notificacion_service.controller;

import com.colegio.notificacion_service.dto.MensajeDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @PostMapping("/enviar")
    public String enviarNotificacion(@RequestBody MensajeDto mensaje) {
        // En un escenario real, aquí usarías JavaMailSender para enviar el correo.
        // Por ahora, simulamos el éxito del envío para la Evaluación 2.
        return "Notificación enviada con éxito a: " + mensaje.getDestinatario();
    }
}