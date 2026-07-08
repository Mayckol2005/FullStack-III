package com.colegio.usuario_service.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleValidationExceptions() {
        MethodArgumentNotValidException mockException = mock(MethodArgumentNotValidException.class);
        BindingResult mockBindingResult = mock(BindingResult.class);

        FieldError errorSimulado = new FieldError("usuario", "email", "El correo es obligatorio");

        when(mockException.getBindingResult()).thenReturn(mockBindingResult);
        when(mockBindingResult.getAllErrors()).thenReturn(List.of(errorSimulado));

        ResponseEntity<Map<String, String>> response = exceptionHandler.handleValidationExceptions(mockException);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
        Map<String, String> errores = response.getBody();
        assertNotNull(errores);
        assertEquals(1, errores.size());
        assertEquals("El correo es obligatorio", errores.get("email"));
    }

    @Test
    void testHandleDataIntegrityViolation() {
        ResponseEntity<Map<String, String>> response =
                exceptionHandler.handleDataIntegrityViolation(
                        new DataIntegrityViolationException("duplicado")
                );

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<String, String> error = response.getBody();
        assertNotNull(error);
        assertEquals(
                "Ya existe un usuario con ese RUT o correo electrónico.",
                error.get("mensaje")
        );
    }
}
