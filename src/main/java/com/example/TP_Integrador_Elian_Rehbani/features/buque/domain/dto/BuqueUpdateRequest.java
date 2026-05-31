package com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.EBuqueEstado;

public record BuqueUpdateRequest(
        String nombre,
        String matriculaImo,
        Double capacidadMaximaToneladas,
        EBuqueEstado estadoOperativo
) {
}
