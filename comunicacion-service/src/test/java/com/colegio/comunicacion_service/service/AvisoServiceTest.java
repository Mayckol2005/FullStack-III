package com.colegio.comunicacion_service.service;

import com.colegio.comunicacion_service.entity.Aviso;
import com.colegio.comunicacion_service.repository.AvisoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvisoServiceTest {
    @Mock private AvisoRepository avisoRepository;
    @Mock private RabbitTemplate rabbitTemplate;
    @InjectMocks private AvisoService avisoService;

    @Test
    void testListarTodos() {
        when(avisoRepository.findAll()).thenReturn(List.of(new Aviso()));
        assertEquals(1, avisoService.listarTodos().size());
    }

    @Test
    void testGuardar() {
        Aviso aviso = new Aviso();
        when(avisoRepository.save(any())).thenReturn(aviso);
        assertNotNull(avisoService.guardar(aviso));
    }
}
