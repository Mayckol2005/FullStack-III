package com.colegio.reporte_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "estudiante-service", path = "/api/estudiantes")
public interface EstudianteClient {

    @GetMapping
    List<Object> listarTodos();
}