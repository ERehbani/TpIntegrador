package com.example.TP_Integrador_Elian_Rehbani.features.puerto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPuertoRepository extends JpaRepository<PuertoEntity, Long> {
    Optional<PuertoEntity> findById(Long puertoId);
}
