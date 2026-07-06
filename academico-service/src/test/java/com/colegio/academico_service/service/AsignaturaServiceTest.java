package com.colegio.academico_service.service;

import com.colegio.academico_service.entity.Asignatura;
import com.colegio.academico_service.repository.AsignaturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AsignaturaServiceTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @InjectMocks
    private AsignaturaService asignaturaService;

    private Asignatura asignaturaMock;

    @BeforeEach
    void setUp() {
        asignaturaMock = new Asignatura();
        asignaturaMock.setId(1L);
        asignaturaMock.setNombre("Matematica");
        asignaturaMock.setCursoId(1L);
        asignaturaMock.setDocenteId(10L);
    }

    @Test
    void testListarTodas() {
        when(asignaturaRepository.findAll())
                .thenReturn(List.of(asignaturaMock));

        List<Asignatura> resultado =
                asignaturaService.listarTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertSame(asignaturaMock, resultado.get(0));

        verify(asignaturaRepository, times(1))
                .findAll();
    }

    @Test
    void testGuardar() {
        when(asignaturaRepository.save(any(Asignatura.class)))
                .thenReturn(asignaturaMock);

        Asignatura resultado =
                asignaturaService.guardar(asignaturaMock);

        assertNotNull(resultado);
        assertSame(asignaturaMock, resultado);

        verify(asignaturaRepository, times(1))
                .save(asignaturaMock);
    }

    @Test
    void testListarPorCurso() {
        when(asignaturaRepository.findByCursoId(1L))
                .thenReturn(List.of(asignaturaMock));

        List<Asignatura> resultado =
                asignaturaService.listarPorCurso(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(
                Long.valueOf(1L),
                resultado.get(0).getCursoId()
        );

        verify(asignaturaRepository, times(1))
                .findByCursoId(1L);
    }

    @Test
    void testListarPorDocente() {
        when(asignaturaRepository.findByDocenteId(10L))
                .thenReturn(List.of(asignaturaMock));

        List<Asignatura> resultado =
                asignaturaService.listarPorDocente(10L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertSame(asignaturaMock, resultado.get(0));
        assertEquals(
                Long.valueOf(10L),
                resultado.get(0).getDocenteId()
        );
        assertEquals(
                "Matematica",
                resultado.get(0).getNombre()
        );

        verify(asignaturaRepository, times(1))
                .findByDocenteId(10L);
    }

    @Test
    void testActualizarExiste() {
        Asignatura detallesAsignatura = new Asignatura();
        detallesAsignatura.setNombre("Fisica");
        detallesAsignatura.setCursoId(2L);
        detallesAsignatura.setDocenteId(20L);

        when(asignaturaRepository.findById(1L))
                .thenReturn(Optional.of(asignaturaMock));

        when(asignaturaRepository.save(asignaturaMock))
                .thenReturn(asignaturaMock);

        Asignatura resultado =
                asignaturaService.actualizar(
                        1L,
                        detallesAsignatura
                );

        assertNotNull(resultado);
        assertSame(asignaturaMock, resultado);
        assertEquals("Fisica", resultado.getNombre());
        assertEquals(
                Long.valueOf(2L),
                resultado.getCursoId()
        );
        assertEquals(
                Long.valueOf(20L),
                resultado.getDocenteId()
        );

        verify(asignaturaRepository, times(1))
                .findById(1L);

        verify(asignaturaRepository, times(1))
                .save(asignaturaMock);
    }

    @Test
    void testActualizarNoExiste() {
        Asignatura detallesAsignatura = new Asignatura();
        detallesAsignatura.setNombre("Fisica");
        detallesAsignatura.setCursoId(2L);
        detallesAsignatura.setDocenteId(20L);

        when(asignaturaRepository.findById(1L))
                .thenReturn(Optional.empty());

        Asignatura resultado =
                asignaturaService.actualizar(
                        1L,
                        detallesAsignatura
                );

        assertNull(resultado);

        verify(asignaturaRepository, times(1))
                .findById(1L);

        verify(asignaturaRepository, never())
                .save(any(Asignatura.class));
    }

    @Test
    void testEliminarExiste() {
        when(asignaturaRepository.existsById(1L))
                .thenReturn(true);

        boolean resultado =
                asignaturaService.eliminar(1L);

        assertTrue(resultado);

        verify(asignaturaRepository, times(1))
                .existsById(1L);

        verify(asignaturaRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void testEliminarNoExiste() {
        when(asignaturaRepository.existsById(1L))
                .thenReturn(false);

        boolean resultado =
                asignaturaService.eliminar(1L);

        assertFalse(resultado);

        verify(asignaturaRepository, times(1))
                .existsById(1L);

        verify(asignaturaRepository, never())
                .deleteById(1L);
    }
}