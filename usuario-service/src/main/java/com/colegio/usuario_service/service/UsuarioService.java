package com.colegio.usuario_service.service;

import com.colegio.usuario_service.entity.Usuario;
import com.colegio.usuario_service.repository.UsuarioRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Aplicamos Circuit Breaker: si falla mucho, se activa el método 'fallback'
    @CircuitBreaker(name = "usuarioServiceCB", fallbackMethod = "fallbackListarUsuarios")
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método de respaldo (Fallback) para cumplir con la Resiliencia de la rúbrica
    public List<Usuario> fallbackListarUsuarios(Exception e) {
        System.out.println("Circuit Breaker activado: El servicio de base de datos no responde.");
        return List.of(); // Retorna lista vacía en lugar de un error 500
    }

    // Buscar por ID
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Eliminar
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Actualizar (Lógica básica)
    public Usuario actualizar(Long id, Usuario usuarioDetalles) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioDetalles.getNombre());
            usuario.setEmail(usuarioDetalles.getEmail());
            usuario.setRol(usuarioDetalles.getRol());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}