package com.colegio.reporte_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resumen estadístico general del establecimiento")
public class ReporteGeneralDto {

    @Schema(description = "Cantidad total de alumnos matriculados", example = "450")
    private Integer totalEstudiantes;

    @Schema(description = "Promedio de notas a nivel institucional", example = "5.8")
    private Double promedioGeneralColegio;

    @Schema(description = "Porcentaje de asistencia diaria", example = "92%")
    private String asistenciaPromedio;

    @Schema(description = "Mensaje informativo del sistema", example = "Operativo")
    private String estado;

    public ReporteGeneralDto(Integer totalEstudiantes, Double promedioGeneralColegio, String asistenciaPromedio, String estado) {
        this.totalEstudiantes = totalEstudiantes;
        this.promedioGeneralColegio = promedioGeneralColegio;
        this.asistenciaPromedio = asistenciaPromedio;
        this.estado = estado;
    }

    // Getters
    public Integer getTotalEstudiantes() { return totalEstudiantes; }
    public Double getPromedioGeneralColegio() { return promedioGeneralColegio; }
    public String getAsistenciaPromedio() { return asistenciaPromedio; }
    public String getEstado() { return estado; }
}