package com.colegio.asistencia_service.controller;

import com.colegio.asistencia_service.entity.Asistencia;
import com.colegio.asistencia_service.service.AsistenciaService;
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
public class AsistenciaControllerTest {

    @Mock
    private AsistenciaService asistenciaService;

    @InjectMocks
    private AsistenciaController asistenciaController;

    private Asistencia asistenciaMock;
    private LocalDate fecha;

    @BeforeEach
    void setUp() {
        fecha = LocalDate.of(2026, 7, 8);

        asistenciaMock = new Asistencia(
                fecha,
                1L,
                10L,
                true,
                ""
        );

        asistenciaMock.setId(50L);
    }

    @Test
    void testListarTodas() {
        when(asistenciaService.listarTodas())
                .thenReturn(List.of(asistenciaMock));

        ResponseEntity<List<Asistencia>> response =
                asistenciaController.listarTodas();

        assertEquals(
                HttpStatus.OK.value(),
                response.getStatusCode().value()
        );

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(
                asistenciaMock,
                response.getBody().get(0)
        );

        verify(asistenciaService, times(1))
                .listarTodas();
    }

    @Test
    void testListarPorCursoYFecha() {
        when(
                asistenciaService.listarPorCursoYFecha(
                        1L,
                        fecha
                )
        ).thenReturn(List.of(asistenciaMock));

        ResponseEntity<List<Asistencia>> response =
                asistenciaController.listarPorCursoYFecha(
                        1L,
                        fecha
                );

        assertEquals(
                HttpStatus.OK.value(),
                response.getStatusCode().value()
        );

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(
                1L,
                response.getBody().get(0).getCursoId()
        );
        assertEquals(
                fecha,
                response.getBody().get(0).getFecha()
        );

        verify(asistenciaService, times(1))
                .listarPorCursoYFecha(
                        1L,
                        fecha
                );
    }

    @Test
    void testGuardar() {
        when(
                asistenciaService.guardar(asistenciaMock)
        ).thenReturn(asistenciaMock);

        ResponseEntity<Asistencia> response =
                asistenciaController.guardar(asistenciaMock);

        assertEquals(
                HttpStatus.CREATED.value(),
                response.getStatusCode().value()
        );

        assertNotNull(response.getBody());
        assertEquals(50L, response.getBody().getId());
        assertEquals(10L, response.getBody().getEstudianteId());
        assertTrue(response.getBody().isPresente());

        verify(asistenciaService, times(1))
                .guardar(asistenciaMock);
    }

    @Test
    void testGuardarLista() {
        Asistencia segundaAsistencia = new Asistencia(
                fecha,
                1L,
                11L,
                false,
                ""
        );

        segundaAsistencia.setId(51L);

        List<Asistencia> asistencias = List.of(
                asistenciaMock,
                segundaAsistencia
        );

        when(
                asistenciaService.guardarLista(asistencias)
        ).thenReturn(asistencias);

        ResponseEntity<List<Asistencia>> response =
                asistenciaController.guardarLista(asistencias);

        assertEquals(
                HttpStatus.OK.value(),
                response.getStatusCode().value()
        );

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().get(0).isPresente());
        assertFalse(response.getBody().get(1).isPresente());

        verify(asistenciaService, times(1))
                .guardarLista(asistencias);
    }
}