package com.colegio.reporte_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@FeignClient(name = "evaluacion-service", path = "/api/evaluaciones")
public interface EvaluacionClient {

    @GetMapping
    List<Map<String, Object>> listarTodas();
}