package com.colegio.academico_service.controller;

import com.colegio.academico_service.entity.Asignatura;
import com.colegio.academico_service.service.AsignaturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AsignaturaControllerTest {

    @Mock
    private AsignaturaService asignaturaService;

    @InjectMocks
    private AsignaturaController controller;

    private Asignatura asignatura;

    @BeforeEach
    void setUp() {
        asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setNombre("Matematica");
        asignatura.setCursoId(1L);
        asignatura.setDocenteId(10L);
    }

    @Test
    void listarAsignaturas() {
        when(asignaturaService.listarTodas())
                .thenReturn(List.of(asignatura));

        ResponseEntity<List<Asignatura>> response =
                controller.listarAsignaturas();

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertSame(
                asignatura,
                response.getBody().get(0)
        );

        verify(asignaturaService, times(1))
                .listarTodas();
    }

    @Test
    void listarPorCurso() {
        when(asignaturaService.listarPorCurso(1L))
                .thenReturn(List.of(asignatura));

        ResponseEntity<List<Asignatura>> response =
                controller.listarPorCurso(1L);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(
                Long.valueOf(1L),
                response.getBody().get(0).getCursoId()
        );

        verify(asignaturaService, times(1))
                .listarPorCurso(1L);
    }

    @Test
    void listarPorDocente() {
        when(asignaturaService.listarPorDocente(10L))
                .thenReturn(List.of(asignatura));

        ResponseEntity<List<Asignatura>> response =
                controller.listarPorDocente(10L);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());

        Asignatura resultado =
                response.getBody().get(0);

        assertEquals(
                Long.valueOf(10L),
                resultado.getDocenteId()
        );

        assertEquals(
                "Matematica",
                resultado.getNombre()
        );

        verify(asignaturaService, times(1))
                .listarPorDocente(10L);
    }

    @Test
    void crearAsignatura() {
        when(asignaturaService.guardar(asignatura))
                .thenReturn(asignatura);

        ResponseEntity<Asignatura> response =
                controller.crearAsignatura(asignatura);

        assertEquals(
                HttpStatus.CREATED,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertSame(
                asignatura,
                response.getBody()
        );

        verify(asignaturaService, times(1))
                .guardar(asignatura);
    }

    @Test
    void actualizarAsignaturaExiste() {
        when(
                asignaturaService.actualizar(
                        1L,
                        asignatura
                )
        ).thenReturn(asignatura);

        ResponseEntity<Asignatura> response =
                controller.actualizarAsignatura(
                        1L,
                        asignatura
                );

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());
        assertSame(
                asignatura,
                response.getBody()
        );

        verify(asignaturaService, times(1))
                .actualizar(
                        1L,
                        asignatura
                );
    }

    @Test
    void actualizarAsignaturaNoExiste() {
        when(
                asignaturaService.actualizar(
                        1L,
                        asignatura
                )
        ).thenReturn(null);

        ResponseEntity<Asignatura> response =
                controller.actualizarAsignatura(
                        1L,
                        asignatura
                );

        assertEquals(
                HttpStatus.NOT_FOUND,
                response.getStatusCode()
        );

        assertNull(response.getBody());

        verify(asignaturaService, times(1))
                .actualizar(
                        1L,
                        asignatura
                );
    }

    @Test
    void eliminarExiste() {
        when(asignaturaService.eliminar(1L))
                .thenReturn(true);

        ResponseEntity<Void> response =
                controller.eliminarAsignatura(1L);

        assertEquals(
                HttpStatus.NO_CONTENT,
                response.getStatusCode()
        );

        assertNull(response.getBody());

        verify(asignaturaService, times(1))
                .eliminar(1L);
    }

    @Test
    void eliminarNoExiste() {
        when(asignaturaService.eliminar(1L))
                .thenReturn(false);

        ResponseEntity<Void> response =
                controller.eliminarAsignatura(1L);

        assertEquals(
                HttpStatus.NOT_FOUND,
                response.getStatusCode()
        );

        assertNull(response.getBody());

        verify(asignaturaService, times(1))
                .eliminar(1L);
    }
}