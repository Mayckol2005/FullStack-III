package com.colegio.usuario_service.controller;

import com.colegio.usuario_service.entity.Usuario;
import com.colegio.usuario_service.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las operaciones de los usuarios del sistema.
 * Provee los endpoints necesarios para crear, buscar, actualizar y eliminar usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Gestión de Usuarios", description = "Endpoints para el mantenimiento de usuarios del colegio")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Obtiene una lista con todos los usuarios registrados en el sistema.
     * @return Lista de objetos Usuario.
     */
    @Operation(summary = "Listar todos los usuarios", description = "Devuelve el catálogo completo de usuarios del colegio")
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.obtenerTodos();
    }

    /**
     * Crea un nuevo usuario en la base de datos (con su contraseña encriptada).
     * @param usuario Objeto JSON con los datos del usuario a crear.
     * @return ResponseEntity con el usuario creado y código HTTP 201 (CREATED).
     */
    @Operation(summary = "Crear un nuevo usuario", description = "Registra un usuario con sus credenciales de acceso")
    @PostMapping("/crear")
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    /**
     * Busca un usuario específico mediante su identificador único.
     * @param id Identificador del usuario.
     * @return ResponseEntity con el usuario (200 OK) o no encontrado (404 NOT FOUND).
     */
    @Operation(summary = "Obtener usuario por ID", description = "Busca los detalles de un usuario según su identificador interno")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Busca un usuario en el sistema utilizando su correo electrónico institucional.
     * @param email Correo electrónico exacto del usuario.
     * @return ResponseEntity con el usuario (200 OK) o no encontrado (404 NOT FOUND).
     */
    @Operation(summary = "Obtener usuario por Email", description = "Útil para procesos de autenticación y login")
    @GetMapping("/buscar-por-email")
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam("email") String email) {
        return usuarioService.obtenerPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza los datos y credenciales de un usuario existente.
     * @param id Identificador del usuario a modificar.
     * @param detalles Objeto JSON con los campos actualizados.
     * @return ResponseEntity con el usuario actualizado y código HTTP 200 (OK).
     */
    @Operation(summary = "Actualizar un usuario", description = "Modifica roles, correos o contraseñas de un perfil existente")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario detalles) {
        return ResponseEntity.ok(usuarioService.actualizar(id, detalles));
    }

    /**
     * Elimina permanentemente a un usuario del sistema.
     * @param id Identificador del usuario a borrar.
     * @return ResponseEntity vacío con código HTTP 204 (NO CONTENT).
     */
    @Operation(summary = "Eliminar un usuario", description = "Borra un registro de usuario de la base de datos")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}