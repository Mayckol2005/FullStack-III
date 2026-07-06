package com.colegio.evaluacion_service.service;

import com.colegio.evaluacion_service.entity.Evaluacion;
import com.colegio.evaluacion_service.repository.EvaluacionRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EvaluacionServiceTest {

    @Mock
    private EvaluacionRepository repository;

    @InjectMocks
    private EvaluacionService service;

    private LocalDate fecha;
    private Evaluacion evaluacion;

    @BeforeEach
    void setUp() {
        fecha = LocalDate.of(2026, 7, 8);

        evaluacion = crearEvaluacion(
                10L,
                5L,
                1,
                6.0
        );
    }

    @Test
    void listarTodasDebeRetornarTodasLasEvaluaciones() {
        when(repository.findAll())
                .thenReturn(List.of(evaluacion));

        List<Evaluacion> resultado =
                service.listarTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertSame(evaluacion, resultado.get(0));

        verify(repository, times(1))
                .findAll();
    }

    @Test
    void listarPorEstudianteDebeRetornarEvaluacionesDelEstudiante() {
        when(repository.findByEstudianteId(10L))
                .thenReturn(List.of(evaluacion));

        List<Evaluacion> resultado =
                service.listarPorEstudiante(10L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(
                Long.valueOf(10L),
                resultado.get(0).getEstudianteId()
        );

        verify(repository, times(1))
                .findByEstudianteId(10L);
    }

    @Test
    void listarPorAsignaturaDebeRetornarEvaluacionesDeLaAsignatura() {
        when(repository.findByAsignaturaId(5L))
                .thenReturn(List.of(evaluacion));

        List<Evaluacion> resultado =
                service.listarPorAsignatura(5L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(
                Long.valueOf(5L),
                resultado.get(0).getAsignaturaId()
        );

        verify(repository, times(1))
                .findByAsignaturaId(5L);
    }

    @Test
    void listarPorEstudianteYAsignaturaDebeRetornarNotasFiltradas() {
        when(
                repository.findByEstudianteIdAndAsignaturaId(
                        10L,
                        5L
                )
        ).thenReturn(List.of(evaluacion));

        List<Evaluacion> resultado =
                service.listarPorEstudianteYAsignatura(
                        10L,
                        5L
                );

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(
                Integer.valueOf(1),
                resultado.get(0).getNumeroEvaluacion()
        );

        verify(repository, times(1))
                .findByEstudianteIdAndAsignaturaId(
                        10L,
                        5L
                );
    }

    @Test
    void guardarDebeCrearEvaluacionCuandoNoExiste() {
        when(
                repository
                        .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                                10L,
                                5L,
                                1
                        )
        ).thenReturn(Optional.empty());

        when(repository.save(evaluacion))
                .thenReturn(evaluacion);

        Evaluacion resultado =
                service.guardar(evaluacion);

        assertNotNull(resultado);
        assertSame(evaluacion, resultado);
        assertEquals(
                Integer.valueOf(1),
                resultado.getNumeroEvaluacion()
        );
        assertEquals(
                Double.valueOf(6.0),
                resultado.getNota()
        );
        assertEquals(fecha, resultado.getFecha());

        verify(repository, times(1))
                .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                        10L,
                        5L,
                        1
                );

        verify(repository, times(1))
                .save(evaluacion);
    }

    @Test
    void guardarDebeActualizarEvaluacionExistenteSinCrearDuplicado() {
        Evaluacion evaluacionExistente = crearEvaluacion(
                10L,
                5L,
                1,
                5.0
        );

        evaluacionExistente.setId(50L);
        evaluacionExistente.setFecha(
                LocalDate.of(2026, 7, 1)
        );

        Evaluacion nuevosDatos = crearEvaluacion(
                10L,
                5L,
                1,
                6.5
        );

        when(
                repository
                        .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                                10L,
                                5L,
                                1
                        )
        ).thenReturn(
                Optional.of(evaluacionExistente)
        );

        when(repository.save(evaluacionExistente))
                .thenReturn(evaluacionExistente);

        Evaluacion resultado =
                service.guardar(nuevosDatos);

        assertNotNull(resultado);
        assertSame(evaluacionExistente, resultado);
        assertEquals(
                Long.valueOf(50L),
                resultado.getId()
        );
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
        assertEquals(
                Double.valueOf(6.5),
                resultado.getNota()
        );
        assertEquals(
                fecha,
                resultado.getFecha()
        );

        verify(repository, times(1))
                .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                        10L,
                        5L,
                        1
                );

        verify(repository, times(1))
                .save(evaluacionExistente);

        verify(repository, never())
                .save(nuevosDatos);
    }

    @Test
    void guardarListaDebeSincronizarN1N2YN3() {
        Evaluacion evaluacionExistente = crearEvaluacion(
                10L,
                5L,
                1,
                5.0
        );

        evaluacionExistente.setId(50L);

        Evaluacion nuevaN1 = crearEvaluacion(
                10L,
                5L,
                1,
                6.5
        );

        Evaluacion nuevaN2 = crearEvaluacion(
                10L,
                5L,
                2,
                5.8
        );

        Evaluacion nuevaN3 = crearEvaluacion(
                10L,
                5L,
                3,
                6.1
        );

        when(
                repository
                        .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                                10L,
                                5L,
                                1
                        )
        ).thenReturn(
                Optional.of(evaluacionExistente)
        );

        when(
                repository
                        .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                                10L,
                                5L,
                                2
                        )
        ).thenReturn(Optional.empty());

        when(
                repository
                        .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                                10L,
                                5L,
                                3
                        )
        ).thenReturn(Optional.empty());

        when(repository.save(any(Evaluacion.class)))
                .thenAnswer(
                        invocacion -> invocacion.getArgument(0)
                );

        List<Evaluacion> resultado =
                service.guardarLista(
                        List.of(
                                nuevaN1,
                                nuevaN2,
                                nuevaN3
                        )
                );

        assertNotNull(resultado);
        assertEquals(3, resultado.size());

        assertSame(
                evaluacionExistente,
                resultado.get(0)
        );

        assertSame(
                nuevaN2,
                resultado.get(1)
        );

        assertSame(
                nuevaN3,
                resultado.get(2)
        );

        assertEquals(
                Long.valueOf(50L),
                resultado.get(0).getId()
        );

        assertEquals(
                Double.valueOf(6.5),
                resultado.get(0).getNota()
        );

        assertEquals(
                Integer.valueOf(1),
                resultado.get(0).getNumeroEvaluacion()
        );

        assertEquals(
                Integer.valueOf(2),
                resultado.get(1).getNumeroEvaluacion()
        );

        assertEquals(
                Integer.valueOf(3),
                resultado.get(2).getNumeroEvaluacion()
        );

        verify(repository, times(1))
                .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                        10L,
                        5L,
                        1
                );

        verify(repository, times(1))
                .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                        10L,
                        5L,
                        2
                );

        verify(repository, times(1))
                .findByEstudianteIdAndAsignaturaIdAndNumeroEvaluacion(
                        10L,
                        5L,
                        3
                );

        verify(repository, times(3))
                .save(any(Evaluacion.class));

        verify(repository, never())
                .save(nuevaN1);
    }

    @Test
    void calcularPromedioDebeRetornarCeroCuandoNoExistenNotas() {
        when(
                repository.findByEstudianteIdAndAsignaturaId(
                        10L,
                        5L
                )
        ).thenReturn(List.of());

        Double resultado =
                service.calcularPromedioAsignatura(
                        10L,
                        5L
                );

        assertEquals(
                Double.valueOf(0.0),
                resultado
        );

        verify(repository, times(1))
                .findByEstudianteIdAndAsignaturaId(
                        10L,
                        5L
                );
    }

    @Test
    void calcularPromedioDebePromediarN1N2YN3YRedondearAUnDecimal() {
        Evaluacion n1 = crearEvaluacion(
                10L,
                5L,
                1,
                6.5
        );

        Evaluacion n2 = crearEvaluacion(
                10L,
                5L,
                2,
                5.8
        );

        Evaluacion n3 = crearEvaluacion(
                10L,
                5L,
                3,
                6.1
        );

        when(
                repository.findByEstudianteIdAndAsignaturaId(
                        10L,
                        5L
                )
        ).thenReturn(
                List.of(
                        n1,
                        n2,
                        n3
                )
        );

        Double resultado =
                service.calcularPromedioAsignatura(
                        10L,
                        5L
                );

        assertEquals(
                Double.valueOf(6.1),
                resultado
        );

        verify(repository, times(1))
                .findByEstudianteIdAndAsignaturaId(
                        10L,
                        5L
                );
    }

    private Evaluacion crearEvaluacion(
            Long estudianteId,
            Long asignaturaId,
            Integer numeroEvaluacion,
            Double nota
    ) {
        Evaluacion nuevaEvaluacion = new Evaluacion();

        nuevaEvaluacion.setEstudianteId(estudianteId);
        nuevaEvaluacion.setAsignaturaId(asignaturaId);
        nuevaEvaluacion.setNumeroEvaluacion(numeroEvaluacion);
        nuevaEvaluacion.setNota(nota);
        nuevaEvaluacion.setFecha(fecha);

        return nuevaEvaluacion;
    }
}