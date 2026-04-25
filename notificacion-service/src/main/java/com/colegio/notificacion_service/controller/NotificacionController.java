package com.colegio.notificacion_service.controller;

import com.colegio.notificacion_service.dto.MensajeDto;
import com.colegio.notificacion_service.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
@Tag(name = "Central de Notificaciones", description = "Endpoints para la emisión de correos y alertas a la comunidad")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Operation(summary = "Enviar correo", description = "Procesa y envía un correo electrónico a un destinatario específico")
    @PostMapping("/enviar")
    public ResponseEntity<String> enviarNotificacion(@Valid @RequestBody MensajeDto mensaje) {
        String resultado = notificacionService.enviarCorreo(mensaje);
        return ResponseEntity.ok(resultado);
    }
}