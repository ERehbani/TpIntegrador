package com.example.TP_Integrador_Elian_Rehbani.features.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findById(Long clienteId);
}
