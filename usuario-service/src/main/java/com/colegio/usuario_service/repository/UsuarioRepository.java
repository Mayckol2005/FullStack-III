package com.colegio.usuario_service.repository;

import com.colegio.usuario_service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Puedes agregar búsquedas personalizadas que sirven para el login después
    Optional<Usuario> findByRut(String rut);
    Optional<Usuario> findByEmail(String email);
}
