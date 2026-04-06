package com.colegio.comunicacion_service.repository;

import com.colegio.comunicacion_service.entity.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long> {
}