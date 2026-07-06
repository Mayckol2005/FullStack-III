package com.colegio.evaluacion_service.controller;

import com.colegio.evaluacion_service.entity.Evaluacion;
import com.colegio.evaluacion_service.service.EvaluacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EvaluacionControllerTest {

    @Mock
    private EvaluacionService service;

    @InjectMocks
    private EvaluacionController controller;

    private LocalDate fecha;
    private Evaluacion evaluacion;

    @BeforeEach
    void setUp() {
        fecha = LocalDate.of(2026, 7, 8);

        evaluacion = crearEvaluacion(
                50L,
                10L,
                5L,
                1,
                6.5
        );
    }

    @Test
    void listarTodasDebeRetornarEvaluacionesYEstadoOk() {
        when(service.listarTodas())
                .thenReturn(List.of(evaluacion));

        ResponseEntity<List<Evaluacion>> response =
                controller.listarTodas();

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertSame(
                evaluacion,
                response.getBody().get(0)
        );

        verify(service, times(1))
                .listarTodas();
    }

    @Test
    void listarPorEstudianteDebeRetornarEvaluacionesYEstadoOk() {
        when(service.listarPorEstudiante(10L))
                .thenReturn(List.of(evaluacion));

        ResponseEntity<List<Evaluacion>> response =
                controller.listarPorEstudiante(10L);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(
                Long.valueOf(10L),
                response.getBody()
                        .get(0)
                        .getEstudianteId()
        );

        verify(service, times(1))
                .listarPorEstudiante(10L);
    }

    @Test
    void listarPorAsignaturaDebeRetornarEvaluacionesYEstadoOk() {
        when(service.listarPorAsignatura(5L))
                .thenReturn(List.of(evaluacion));

        ResponseEntity<List<Evaluacion>> response =
                controller.listarPorAsignatura(5L);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(
                Long.valueOf(5L),
                response.getBody()
                        .get(0)
                        .getAsignaturaId()
        );

        verify(service, times(1))
                .listarPorAsignatura(5L);
    }

    @Test
    void listarPorEstudianteYAsignaturaDebeRetornarNotasFiltradas() {
        when(
                service.listarPorEstudianteYAsignatura(
                        10L,
                        5L
                )
        ).thenReturn(List.of(evaluacion));

        ResponseEntity<List<Evaluacion>> response =
                controller.listarPorEstudianteYAsignatura(
                        10L,
                        5L
                );

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());

        Evaluacion resultado =
                response.getBody().get(0);

        assertEquals(
                Long.valueOf(10L),
                resultado.getEstudianteId()
        );

        assertEquals(
                Long.valueOf(5L),
                resultado.getAsignaturaId()
        );

        assertEquals(
                Integer.valueOf(1),
                resultado.getNumeroEvaluacion()
        );

        verify(service, times(1))
                .listarPorEstudianteYAsignatura(
                        10L,
                        5L
                );
    }

    @Test
    void guardarDebeRetornarEvaluacionYEstadoCreated() {
        when(service.guardar(evaluacion))
                .thenReturn(evaluacion);

        ResponseEntity<Evaluacion> response =
                controller.guardar(evaluacion);

        assertEquals(
                HttpStatus.CREATED,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertSame(
                evaluacion,
                response.getBody()
        );

        assertEquals(
                Long.valueOf(50L),
                response.getBody().getId()
        );

        assertEquals(
                Integer.valueOf(1),
                response.getBody()
                        .getNumeroEvaluacion()
        );

        assertEquals(
                Double.valueOf(6.5),
                response.getBody().getNota()
        );

        verify(service, times(1))
                .guardar(evaluacion);
    }

    @Test
    void guardarListaDebeSincronizarN1N2YN3YRetornarEstadoOk() {
        Evaluacion n1 = crearEvaluacion(
                50L,
                10L,
                5L,
                1,
                6.5
        );

        Evaluacion n2 = crearEvaluacion(
                51L,
                10L,
                5L,
                2,
                5.8
        );

        Evaluacion n3 = crearEvaluacion(
                52L,
                10L,
                5L,
                3,
                6.1
        );

        List<Evaluacion> evaluaciones = List.of(
                n1,
                n2,
                n3
        );

        when(service.guardarLista(evaluaciones))
                .thenReturn(evaluaciones);

        ResponseEntity<List<Evaluacion>> response =
                controller.guardarLista(evaluaciones);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().size());

        assertEquals(
                Integer.valueOf(1),
                response.getBody()
                        .get(0)
                        .getNumeroEvaluacion()
        );

        assertEquals(
                Integer.valueOf(2),
                response.getBody()
                        .get(1)
                        .getNumeroEvaluacion()
        );

        assertEquals(
                Integer.valueOf(3),
                response.getBody()
                        .get(2)
                        .getNumeroEvaluacion()
        );

        assertEquals(
                Double.valueOf(6.5),
                response.getBody()
                        .get(0)
                        .getNota()
        );

        assertEquals(
                Double.valueOf(5.8),
                response.getBody()
                        .get(1)
                        .getNota()
        );

        assertEquals(
                Double.valueOf(6.1),
                response.getBody()
                        .get(2)
                        .getNota()
        );

        verify(service, times(1))
                .guardarLista(evaluaciones);
    }

    @Test
    void obtenerPromedioDebeRetornarPromedioYEstadoOk() {
        when(
                service.calcularPromedioAsignatura(
                        10L,
                        5L
                )
        ).thenReturn(6.1);

        ResponseEntity<Double> response =
                controller.obtenerPromedio(
                        10L,
                        5L
                );

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());

        assertEquals(
                Double.valueOf(6.1),
                response.getBody()
        );

        verify(service, times(1))
                .calcularPromedioAsignatura(
                        10L,
                        5L
                );
    }

    private Evaluacion crearEvaluacion(
            Long id,
            Long estudianteId,
            Long asignaturaId,
            Integer numeroEvaluacion,
            Double nota
    ) {
        Evaluacion nuevaEvaluacion = new Evaluacion();

        nuevaEvaluacion.setId(id);
        nuevaEvaluacion.setEstudianteId(estudianteId);
        nuevaEvaluacion.setAsignaturaId(asignaturaId);
        nuevaEvaluacion.setNumeroEvaluacion(numeroEvaluacion);
        nuevaEvaluacion.setNota(nota);
        nuevaEvaluacion.setFecha(fecha);

        return nuevaEvaluacion;
    }
}