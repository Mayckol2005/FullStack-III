package com.colegio.usuario_service.service;

import com.colegio.usuario_service.entity.Usuario;
import com.colegio.usuario_service.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void testObtenerTodos_DebeRetornarLista() {
        // Configuramos el comportamiento del Mock (Simulacro)
        Usuario user = new Usuario("1-9", "Test User", "test@colegio.cl", "123", "ADMIN");
        when(usuarioRepository.findAll()).thenReturn(List.of(user));

        // Ejecutamos el método del servicio
        List<Usuario> resultado = usuarioService.obtenerTodos();

        // Verificamos los resultados
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Test User", resultado.get(0).getNombre());

        // Verificamos que el repositorio fue llamado exactamente una vez
        verify(usuarioRepository, times(1)).findAll();
    }
}