package com.colegio.usuario_service.service;

import com.colegio.usuario_service.entity.Usuario;
import com.colegio.usuario_service.repository.UsuarioRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de manejar la lógica de negocio de los usuarios.
 * Incluye la encriptación de contraseñas y el manejo de resiliencia mediante CircuitBreaker.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    /**
     * Obtiene todos los usuarios. Si la base de datos falla, se activa el CircuitBreaker.
     * @return Lista de usuarios del sistema.
     */
    @CircuitBreaker(name = "usuarioServiceCB", fallbackMethod = "fallbackListarUsuarios")
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Guarda un nuevo usuario. Intercepta la contraseña en texto plano y la encripta usando BCrypt.
     * @param usuario Objeto usuario a guardar.
     * @return El usuario guardado con la contraseña encriptada.
     */
    public Usuario guardar(Usuario usuario) {
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }

    /**
     * Método de contingencia (Fallback) para cuando la búsqueda de usuarios falla.
     * @param e Excepción capturada por el CircuitBreaker.
     * @return Lista vacía por defecto.
     */
    public List<Usuario> fallbackListarUsuarios(Exception e) {
        return List.of();
    }

    /**
     * Busca un usuario por su ID primario.
     * @param id ID del usuario.
     * @return Optional con el usuario si existe.
     */
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Busca un usuario por su correo electrónico.
     * @param email Correo electrónico exacto.
     * @return Optional con el usuario si existe.
     */
    public Optional<Usuario> obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Elimina un usuario por su ID.
     * @param id ID del usuario.
     */
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Actualiza los datos de un usuario. Si se incluye una nueva contraseña, esta se encripta.
     * @param id ID del usuario a modificar.
     * @param usuarioDetalles Datos nuevos del usuario.
     * @return Usuario actualizado.
     * @throws RuntimeException Si el usuario no existe.
     */
    public Usuario actualizar(Long id, Usuario usuarioDetalles) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioDetalles.getNombre());
            usuario.setEmail(usuarioDetalles.getEmail());
            usuario.setRol(usuarioDetalles.getRol());

            if (usuarioDetalles.getPassword() != null && !usuarioDetalles.getPassword().isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(usuarioDetalles.getPassword()));
            }

            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}