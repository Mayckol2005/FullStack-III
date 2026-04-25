package com.colegio.notificacion_service.controller;

import com.colegio.notificacion_service.dto.MensajeDto;
import com.colegio.notificacion_service.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarNotificacion(@Valid @RequestBody MensajeDto mensaje) {
        String resultado = notificacionService.enviarCorreo(mensaje);
        return ResponseEntity.ok(resultado);
    }
}