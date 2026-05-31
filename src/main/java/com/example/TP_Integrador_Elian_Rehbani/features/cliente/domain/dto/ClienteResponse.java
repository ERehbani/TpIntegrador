package com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto;

public record ClienteResponse(
        Long id,
        String razonSocial,
        String dni,
        Boolean esVip,
        Boolean activo
) {
}
