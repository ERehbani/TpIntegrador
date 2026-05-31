package com.example.TP_Integrador_Elian_Rehbani.features.contenedor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IContenedorRepository extends JpaRepository<ContenedorEntity, Long> {
    Optional<ContenedorEntity> findById(Long contenedorId);
    List<ContenedorEntity> findByBuqueIdAndActivoTrue(Long buqueId);
}
