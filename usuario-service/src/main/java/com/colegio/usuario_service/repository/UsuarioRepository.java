package com.colegio.usuario_service.repository;

import com.colegio.usuario_service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}