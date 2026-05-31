package com.example.TP_Integrador_Elian_Rehbani.features.buque;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBuqueRepository extends JpaRepository<BuqueEntity, Long> {
    Optional<BuqueEntity> findById(Long buqueId);
}
