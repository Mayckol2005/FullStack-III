package com.colegio.academico_service.controller;

import com.colegio.academico_service.entity.Curso;
import com.colegio.academico_service.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CursoControllerTest {

    @Mock
    private CursoService cursoService;

    @InjectMocks
    private CursoController cursoController;

    private Curso cursoMock;

    @BeforeEach
    void setUp() {
        // Inicializamos el mock con datos reales para evitar NullPointerException en los métodos de validación
        cursoMock = new Curso("2do", "A", "Básica");
    }

    @Test
    void testListarCursos() {
        when(cursoService.listarTodos()).thenReturn(List.of(cursoMock));

        ResponseEntity<List<Curso>> response = cursoController.listarCursos();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(cursoService, times(1)).listarTodos();
    }

    @Test
    void testObtenerCurso_Encontrado() {
        when(cursoService.obtenerPorId(1L)).thenReturn(cursoMock);

        ResponseEntity<Curso> response = cursoController.obtenerCurso(1L);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(cursoService, times(1)).obtenerPorId(1L);
    }

    @Test
    void testObtenerCurso_NoEncontrado() {
        when(cursoService.obtenerPorId(99L)).thenReturn(null);

        ResponseEntity<Curso> response = cursoController.obtenerCurso(99L);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
        verify(cursoService, times(1)).obtenerPorId(99L);
    }

    @Test
    void testCrearCurso_Exitoso() {
        when(cursoService.listarTodos()).thenReturn(List.of());
        when(cursoService.guardar(any(Curso.class))).thenReturn(cursoMock);

        ResponseEntity<?> response = cursoController.crearCurso(cursoMock);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(cursoService, times(1)).listarTodos();
        verify(cursoService, times(1)).guardar(any(Curso.class));
    }

    @Test
    void testCrearCurso_Duplicado() {
        // Simulamos que ya existe un curso exactamente igual guardado en la base de datos
        Curso cursoExistente = new Curso("2do", "A", "Básica");
        when(cursoService.listarTodos()).thenReturn(List.of(cursoExistente));

        // Ejecutamos la petición de creación
        ResponseEntity<?> response = cursoController.crearCurso(cursoMock);

        // Verificamos que lance un error 409 CONFLICT y retorne el mensaje de advertencia correcto
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatusCode().value());
        assertEquals("El curso 2do° A de nivel Básica ya se encuentra registrado.", response.getBody());
        
        // 🛡️ Regla de oro: Verificamos que NUNCA se invoque al método guardar de la base de datos si el curso es repetido
        verify(cursoService, never()).guardar(any(Curso.class));
    }
}