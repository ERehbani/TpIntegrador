package com.example.TP_Integrador_Elian_Rehbani.features.cliente.domain.dto;

public record ClienteUpdateRequest(
        String razonSocial,
        Boolean esVip
) {
}
