package com.colegio.notificacion_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estructura de datos para el envío de correos o alertas a la comunidad escolar")
public class MensajeDto {

    @Schema(description = "Correo electrónico institucional o personal del receptor", example = "apoderado@liceo.cl")
    private String destinatario;

    @Schema(description = "Título o motivo principal del comunicado", example = "Citación a Reunión de Apoderados")
    private String asunto;

    @Schema(description = "Cuerpo detallado del mensaje o alerta", example = "Estimado apoderado, se cita a reunión el próximo martes a las 19:00 hrs en la sala del curso.")
    private String contenido;

    public MensajeDto() {}

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
}