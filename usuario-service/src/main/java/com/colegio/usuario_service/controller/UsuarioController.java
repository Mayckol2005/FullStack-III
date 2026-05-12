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

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@Tag(name = "Gestión de Usuarios", description = "Endpoints para el mantenimiento de usuarios del colegio")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista completa de los usuarios registrados.")
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.obtenerTodos();
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Valida y registra un nuevo usuario en la base de datos.")
    @PostMapping("/crear")
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @Operation(summary = "Obtener usuario por ID", description = "Busca un usuario específico utilizando su ID autogenerado.")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener usuario por Email", description = "Busca un usuario específico utilizando su correo electrónico. Usado principalmente por auth-service.")
    @GetMapping("/buscar-por-email")
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam("email") String email) {
        return usuarioService.obtenerPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ELIMINAMOS EL @Valid DE AQUÍ PARA QUE ACEPTE ACTUALIZACIONES SIN CONTRASEÑA
    @Operation(summary = "Actualizar un usuario", description = "Sobrescribe los datos de un usuario existente. Mantiene la clave si no se envía.")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario detalles) {
        return ResponseEntity.ok(usuarioService.actualizar(id, detalles));
    }

    @Operation(summary = "Eliminar un usuario", description = "Elimina físicamente un usuario mediante su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}