package com.colegio.asistencia_service.service;

import com.colegio.asistencia_service.entity.Asistencia;
import com.colegio.asistencia_service.repository.AsistenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AsistenciaServiceTest {

    @Mock
    private AsistenciaRepository asistenciaRepository;

    @InjectMocks
    private AsistenciaService asistenciaService;

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
    }

    @Test
    void testListarTodas() {
        when(asistenciaRepository.findAll())
                .thenReturn(List.of(asistenciaMock));

        List<Asistencia> resultado =
                asistenciaService.listarTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(asistenciaMock, resultado.get(0));

        verify(asistenciaRepository, times(1))
                .findAll();
    }

    @Test
    void testListarPorCursoYFecha() {
        when(
                asistenciaRepository.findByCursoIdAndFecha(
                        1L,
                        fecha
                )
        ).thenReturn(List.of(asistenciaMock));

        List<Asistencia> resultado =
                asistenciaService.listarPorCursoYFecha(
                        1L,
                        fecha
                );

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getCursoId());
        assertEquals(fecha, resultado.get(0).getFecha());

        verify(asistenciaRepository, times(1))
                .findByCursoIdAndFecha(
                        1L,
                        fecha
                );
    }

    @Test
    void testGuardarCreaNuevaAsistenciaCuandoNoExiste() {
        when(
                asistenciaRepository.findByEstudianteIdAndFecha(
                        10L,
                        fecha
                )
        ).thenReturn(Optional.empty());

        when(asistenciaRepository.save(asistenciaMock))
                .thenReturn(asistenciaMock);

        Asistencia resultado =
                asistenciaService.guardar(asistenciaMock);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getEstudianteId());
        assertEquals(1L, resultado.getCursoId());
        assertTrue(resultado.isPresente());

        verify(asistenciaRepository, times(1))
                .findByEstudianteIdAndFecha(
                        10L,
                        fecha
                );

        verify(asistenciaRepository, times(1))
                .save(asistenciaMock);
    }

    @Test
    void testGuardarActualizaAsistenciaCuandoYaExiste() {
        Asistencia asistenciaExistente = new Asistencia(
                fecha,
                1L,
                10L,
                true,
                ""
        );

        asistenciaExistente.setId(50L);

        Asistencia nuevosDatos = new Asistencia(
                fecha,
                1L,
                10L,
                false,
                "Ausencia informada"
        );

        when(
                asistenciaRepository.findByEstudianteIdAndFecha(
                        10L,
                        fecha
                )
        ).thenReturn(Optional.of(asistenciaExistente));

        when(
                asistenciaRepository.save(asistenciaExistente)
        ).thenReturn(asistenciaExistente);

        Asistencia resultado =
                asistenciaService.guardar(nuevosDatos);

        assertNotNull(resultado);
        assertEquals(50L, resultado.getId());
        assertEquals(1L, resultado.getCursoId());
        assertFalse(resultado.isPresente());
        assertEquals(
                "Ausencia informada",
                resultado.getObservacion()
        );

        verify(asistenciaRepository, times(1))
                .findByEstudianteIdAndFecha(
                        10L,
                        fecha
                );

        verify(asistenciaRepository, times(1))
                .save(asistenciaExistente);

        verify(
                asistenciaRepository,
                never()
        ).save(nuevosDatos);
    }

    @Test
    void testGuardarListaSincronizaTodasLasAsistencias() {
        Asistencia asistenciaUno = new Asistencia(
                fecha,
                1L,
                10L,
                true,
                ""
        );

        Asistencia asistenciaDos = new Asistencia(
                fecha,
                1L,
                11L,
                false,
                ""
        );

        when(
                asistenciaRepository.findByEstudianteIdAndFecha(
                        anyLong(),
                        eq(fecha)
                )
        ).thenReturn(Optional.empty());

        when(
                asistenciaRepository.save(any(Asistencia.class))
        ).thenAnswer(invocacion -> invocacion.getArgument(0));

        List<Asistencia> resultado =
                asistenciaService.guardarLista(
                        List.of(
                                asistenciaUno,
                                asistenciaDos
                        )
                );

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.get(0).isPresente());
        assertFalse(resultado.get(1).isPresente());

        verify(asistenciaRepository, times(2))
                .findByEstudianteIdAndFecha(
                        anyLong(),
                        eq(fecha)
                );

        verify(asistenciaRepository, times(2))
                .save(any(Asistencia.class));
    }
}