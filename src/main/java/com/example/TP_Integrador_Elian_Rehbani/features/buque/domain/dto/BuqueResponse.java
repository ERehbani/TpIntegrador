package com.example.TP_Integrador_Elian_Rehbani.features.buque.domain.dto;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.EBuqueEstado;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.domain.dto.PuertoResponse;

import java.util.List;

public record BuqueResponse(
        Long id,
        String message,
        String matriculaImo,
        Double capacidadMaximaToneladas,
        EBuqueEstado estadoOperativo,
        Boolean activo,
        List<PuertoResponse> puertos,
        Double pesoAcumulado
) {
}
